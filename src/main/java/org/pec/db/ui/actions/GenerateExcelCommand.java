package org.pec.db.ui.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.pec.db.entities.Person;
import org.pec.db.ui.PersonContainer;
import org.pec.db.ui.PersonList;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class GenerateExcelCommand implements Command<File>{
	
	private final Provider<PersonContainer> containerP;
	
	@Inject
	public GenerateExcelCommand(Provider<PersonContainer> containerP) {
		this.containerP = containerP;
	}
	
	public File execute(){
		File file = null;
		FileOutputStream out = null;
		// create a new file
		try {
			try{
				file = File.createTempFile("basedatos", ".xls");
				System.out.println(file.getAbsolutePath());
				out = new FileOutputStream(file);
				// create a new workbook
				Workbook wb = new HSSFWorkbook();
				Sheet     s = wb.createSheet("basedatos");
				Row row = null;
				int rownum = 0;
				for(Person p : containerP.get().getItemIds()){
					row = s.createRow(rownum++);
					createCells(row, p);
				}
				wb.write(out);
			}finally{
				if ( out != null)
					out.close();
			}
		} catch (Exception e) {
			throw new Error(e);
		}
		return file;
	}
	
	@SuppressWarnings("unchecked")
	private void createCells(Row row,Person bean) throws Exception{
		Map<String,Object> dBean = BeanUtils.describe(bean);
		int i = 0;
		Cell cell = null;
		for(String string : PersonList.NATURAL_COL_ORDER){
			cell = row.createCell(i++);
			Object value = dBean.get(string);
			if(value instanceof Date){
				cell.setCellValue((Date)value);
			}else{
				cell.setCellValue(String.valueOf(value));
			}
		}
	}
}
