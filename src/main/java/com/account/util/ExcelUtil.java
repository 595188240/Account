package com.account.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class ExcelUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    private static final String EXCEL_XLS = "xls";

    private static final String EXCEL_XLSX = "xlsx";

    /**
     * @param file 读取excel
     * @return List
     */
    public static List<List<Object>> readExcel(File file) throws IOException {
        String fileName = file.getName();
        String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName
                .substring(fileName.lastIndexOf(".") + 1);
        if (EXCEL_XLS.equals(extension)) {
            return read2003Excel(file);
        } else if (EXCEL_XLSX.equals(extension)) {
            return read2007Excel(file);
        } else {
            throw new IOException("不支持的文件类型");
        }
    }

    /**
     * @param file 2003excel
     * @return List
     */
    private static List<List<Object>> read2003Excel(File file)
            throws IOException {
        HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));
        return getRows(hwb);
    }

    /**
     * @param file excel2007文件
     * @return List
     */
    private static List<List<Object>> read2007Excel(File file)
            throws IOException {
        // 构造 XSSFWorkbook 对象，strPath 传入文件路径
        XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
        return getRows(xwb);
    }

    /**
     * 转换为数据list
     * @param xwb workbook
     */
    private static List<List<Object>> getRows(Workbook xwb) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        List<List<Object>> list = new LinkedList<>();
        // 读取第一章表格内容
        Sheet sheet = xwb.getSheetAt(0);
        Object value;
        Row row;
        Cell cell;
        int counter = 0;
        for (int i = sheet.getFirstRowNum(); counter < sheet
                .getPhysicalNumberOfRows(); i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            } else {
                counter++;
            }
            List<Object> linked = new LinkedList<Object>();
            for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                if (cell == null) {
                    linked.add("");
                    continue;
                }
                switch (cell.getCellType()) {
                    case XSSFCell.CELL_TYPE_NUMERIC:
                        if (cell.getCellStyle().getDataFormatString()
                                .contains("yyyy\"年\"m\"月\"d\"日\"")
                                || cell.getCellStyle().getDataFormatString()
                                .contains("m/d/yy")) {
                            value = sdf.format(HSSFDateUtil.getJavaDate(cell
                                    .getNumericCellValue()));
                        } else {
                            value = cell.toString().trim();
                        }
                        break;
                    default:
                        value = cell.toString().trim();
                }
                linked.add(value);
            }
            list.add(linked);
        }
        return list;
    }


    /**
     * @param path     路径
     * @param response 返回请求
     * @description 导出文件下载
     */
    public static void download(String path, HttpServletResponse response) throws IOException {
        InputStream fis = null;
//        OutputStream toClient  = null;
        try {
            // path是指下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 以流的形式下载文件。
            fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            int count = 0;
            while (((count = fis.read(buffer)) > 0)) {

            }
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            try (OutputStream toClient = new BufferedOutputStream(response.getOutputStream())){
                response.setContentType("application/vnd.ms-excel;charset=utf-8");
                toClient.write(buffer);
                toClient.flush();
            }

        } catch (IOException ex) {
            logger.error(ex.getMessage(),ex);
        }finally {
            if(fis!=null){
                fis.close();
            }
        }
    }

    /**
     * @param filePath 文件路径
     * @description 删除文件
     */
    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        // 文件存在时删除
        if (file.exists()) {
            if(!file.delete()){
                //删除失败
            };
        }
    }

    /**
     * 删除文件夹
     *
     * @param filePath 文件夹路径
     */
    public static void deleteDir(String filePath) {
        File file = new File(filePath);
        deleteFile(file);
        file = new File(file.getParent());
        deleteFile(file);
    }

    /**
     * 删除文件
     *
     * @param file 文件
     */
    public static void deleteFile(File file) {
        // 判断文件是否存在
        if (file.exists()) {
            // 判断是否是文件
            if (file.isFile()) {
                if(!file.delete()){
                    //删除失败
                }
            } else if (file.isDirectory()) {
                // 声明目录下所有的文件 files[];
                File[] files = file.listFiles();
                // 遍历目录下所有的文件
                for (int i = 0; i < files.length; i++) {
                    // 把每个文件迭代删除
                    deleteFile(files[i]);
                }
            }
            if(!file.delete()){
                //删除失败
            };
        } else {
            System.out.println("所删除的文件不存在！" + '\n');
        }
    }

    /**
     * @description 内容转化为excel表格形式
     */
    public static Workbook rows2Cells(List<List<Object>> content)
            throws IOException {

        // Workbook wb = new HSSFWorkbook();
        Workbook wb = new SXSSFWorkbook(10000);
        Sheet sheet = wb.createSheet("sheet1");
        for (int i = 0; i < content.size(); i++) {
            Row row = sheet.createRow(i);
            List<Object> item = content.get(i);
            int len = item.size();
            Long a = System.currentTimeMillis();
            for (int j = 0; j < len; j++) {
                String value = "";
                if (item.get(j) != null) {
                    value = item.get(j).toString();
                }
                row.createCell(j).setCellValue(value);
            }
        }
        return wb;
    }

    public static Sheet rows2Cells(List<List<Object>> content, Sheet sheet)
            throws IOException {
        // Workbook wb = new HSSFWorkbook();
        for (int i = 0; i < content.size(); i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j < content.get(i).size(); j++) {
                String value = "";
                if (content.get(i).get(j) != null) {
                    value = content.get(i).get(j).toString();
                }
                row.createCell(j).setCellValue(value);
            }
        }
        return sheet;
    }

    /**
     * @description 数据写入excel文件
     */
    public static void writeExcel(Workbook wb, File file) {
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream(file);
            wb.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }


    /**
     * 导出excel
     */
    public static void exportExcel(List data, String name, String[] title, String[] field,
                                   HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {
        String[][] content = new String[data.size()][title.length];
        //第一步
        Workbook wb = new XSSFWorkbook();
        //第二步（可起名）
        Sheet sheet = wb.createSheet();
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        Row row = sheet.createRow(0);
        //声明列对象
        Cell cell;
        for (int i = 0; i < data.size(); i++) {
            content[i] = new String[title.length];
            JSONObject obj = (JSONObject) JSONObject.toJSON(data.get(i));
            for (int j = 0; j < field.length; j++) {
                content[i][j] = obj.getString(field[j]);
            }
        }
        //创建标题
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
        }
        //创建内容
        for (int i = 0; i < data.size(); i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < content[i].length; j++) {
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(content[i][j]);
            }
        }
        //1.设置文件ContentType类型
        response.setContentType("APPLICATION/OCTET-STREAM");
        //2.设置文件头：最后一个参数是设置下载文件名
        String userAgent = request.getHeader("user-agent").toLowerCase();
        String fileName;
        if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
            // win10 ie edge 浏览器 和其他系统的ie
            fileName = URLEncoder.encode(name, "UTF-8");
        } else {
            // 其他
            fileName = new String(name.getBytes("UTF-8"), "iso-8859-1");
        }
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        try (OutputStream out = response.getOutputStream()) {
            wb.write(out);
            out.flush();
            wb.close();
        } catch (Exception e) {
            logger.error("导出失败！", e);
        }
    }

}
