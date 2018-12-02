package poi;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @author dmf
 */
public class ExcelParseUtil {

    private static final String SUFFIX_2003 = ".xls";
    private static final String SUFFIX_2007 = ".xlsx";

    public static Workbook initWorkBook(String mFilePath) throws IOException {
        //关闭流
        InputStream is = new FileInputStream(new File(mFilePath));
        Workbook workbook = null;
        if (mFilePath.endsWith(SUFFIX_2003)) {
            workbook = new HSSFWorkbook(is);
        } else if (mFilePath.endsWith(SUFFIX_2007)) {
            workbook = new XSSFWorkbook(is);
        }
        return workbook;
    }

    /**
     *  待解决：通过多线程解析多sheet
     */
    public static List<Map<String, Object>> parseWorkbook(Workbook workbook, String[] fields) {
        List<Map<String, Object>> list = Lists.newArrayList();
        int numOfSheet = workbook.getNumberOfSheets();
        for (int i = 0; i < numOfSheet; ++i) {
            Sheet sheet = workbook.getSheetAt(i);
            parseSheet(sheet, list,fields);
        }
        return list;
    }

    private static void parseSheet(Sheet sheet, List<Map<String, Object>> list, String[] fields) {
        Row row;
        if (null != sheet) {
            //获取最大行数
            int rowNum = sheet.getPhysicalNumberOfRows();
            //通过第一行获取最大列
            row = sheet.getRow(0);
            int column = row.getPhysicalNumberOfCells();
            //获取head
            String[] columns = fields;
            String data;
            Map<String, Object> map;
            for (int i = 1; i < rowNum; i++) {
                map = Maps.newHashMap();
                row = sheet.getRow(i);
                if (row != null) {
                    for (int j = 0; j < column; j++) {
                        data = parseCell(row.getCell(j),columns[j]);
                        map.put(columns[j], data);
                    }
                }
                list.add(map);
            }
        }
    }

    private static String[] parseRowHead(Row row) {
        String[] columns = null;
        if (null != row) {
            int column = row.getPhysicalNumberOfCells();
            columns = new String[column];
            for (int i = 0; i < column; i++) {
                columns[i] = parseCell(row.getCell(i),"");
            }
        }
        return columns;
    }

    private static String parseCell(Cell cell, String column) {
        if (null != cell) {
        	if ("startDateStr".equals(column)
        			|| "saveInDateStr".equals(column)
        			|| "wasteDateStr".equals(column)
        			|| "completedDateStr".equals(column)){
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        		//StringUtil.isNullOrEmpty(cell.getStringCellValue());
                //加上这一句判断是由于excel文件下载为文档类型，当编写后变为日期类型，这样可以处理未修改的日期数据
        		if(cell.getCellType()==1){
        			return cell.getStringCellValue();
        		}
        		return cell.getDateCellValue()!=null ? sdf.format(cell.getDateCellValue()) : "";
			}
			cell.setCellType(Cell.CELL_TYPE_STRING);
			return cell.getStringCellValue();				
        }
        return "";
    }
}
