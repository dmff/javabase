package poi;


import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class MainEntrance {

    public static void main(String[] args) throws IOException {
        Workbook workbook = ExcelParseUtil.initWorkBook("D:\\Backup\\Downloads\\test.xlsx");
        /*final Sheet sheet = workbook.getSheetAt(0);
        final Row row = sheet.getRow(2);
        final Cell cell = row.getCell(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(cell.getDateCellValue()));*/
        final List<Map<String, Object>> list = ExcelParseUtil.parseWorkbook(workbook, new String[]{"name", "birthday"});
        System.out.println(list);
    }
}
