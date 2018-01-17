package biubiu.array;

import java.io.File;
import java.io.FilenameFilter;

public class FileList {

	public static void main(String[] args) {
		File filedir = new File("F:\\23114104java\\heima\\crm\\src\\com\\crm\\base");
		
		if(!(filedir.exists()&&filedir.isDirectory())){
			throw new RuntimeException("文件目录不存在...");
		}
		
		String[] fileList = filedir.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".java");
			}
		});
		
		for (String string : fileList) {
			System.out.println(string);
		}
	}
}
