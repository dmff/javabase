package poi;

import org.apache.poi.hssf.usermodel.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TestPoi2 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		List lists = new ArrayList();
		/*List<Person> lists = new ArrayList<Person>();
		
		lists.add(new Person("卡牌", "男", "1"));
		lists.add(new Person("压缩", "男", "2"));
		lists.add(new Person("石头人", "男", "3"));*/
		
		//1.创建工作薄对象
		HSSFWorkbook wb = new HSSFWorkbook();
		
		//2.创建标题样式
		HSSFCellStyle headStyle = headStyle(wb);
		
		//3.创建内容样式
		HSSFCellStyle contentStyle = contentStyle(wb);
		
		//4.创建表
		HSSFSheet sheet = wb.createSheet("人员信息");
		sheet.setDefaultColumnWidth(30);  //设置表的默认列宽
		
		//创建标题行
		HSSFRow headRow = sheet.createRow(0);
		HSSFCell headCell = headRow.createCell(0);  //创建标题行第一列
		headCell.setCellValue("姓名");
		
		HSSFCell headcell2 = headRow.createCell(1);
        headcell2.setCellValue("性别");
        
        HSSFCell headcell3 = headRow.createCell(2);
        headcell3.setCellValue("年龄");
        
        headCell.setCellStyle(headStyle);
        headcell3.setCellStyle(headStyle);
        headcell2.setCellStyle(headStyle);
        
        //为内容行添加数据和样式
        /*for (int i=0; i<lists.size() ;i++) {
			HSSFRow contentRow = sheet.createRow(i+1);
			
			//设置当前行的每一列数据，从索引0开始
			HSSFCell cell1 = contentRow.createCell(0);
			cell1.setCellValue(lists.get(i).getName());
			
			HSSFCell cell2 = contentRow.createCell(1);
			cell2.setCellValue(lists.get(i).getSex());
			
			HSSFCell cell3 = contentRow.createCell(2);
			cell3.setCellValue(lists.get(i).getId());
			
			//设置每一列的样式
			cell1.setCellStyle(contentStyle);
			cell2.setCellStyle(contentStyle);
			cell3.setCellStyle(contentStyle);
		}*/
        
        wb.write(new FileOutputStream(new File("e:/poi2.xls")));
	}

	/**
	 * 创建内容行样式
	 * @param wb
	 * @return
	 */
	private static HSSFCellStyle contentStyle(HSSFWorkbook wb) {
		HSSFCellStyle headStyle = wb.createCellStyle();
		HSSFFont contentFont = wb.createFont();
		contentFont.setFontName("微软雅黑");
		contentFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		contentFont.setColor(HSSFFont.COLOR_NORMAL);
		
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headStyle.setFont(contentFont);
		return headStyle;
	}

	/**
	 * 创建标题行样式
	 * @param wb
	 * @return
	 */
	private static HSSFCellStyle headStyle(HSSFWorkbook wb) {
		
		HSSFCellStyle headStyle = wb.createCellStyle();
		
		HSSFFont headFont = wb.createFont();
		headFont.setFontName("微软雅黑");
		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headFont.setColor(HSSFFont.COLOR_RED);
		
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headStyle.setFont(headFont);
		return headStyle;
	}
}
