package com.qunar.fintech.plat.admin.query.export.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qunar.fintech.plat.admin.query.export.annotation.Excel;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class ExcelExportEngine {
	
	private  SXSSFWorkbook workbook;
	private  Sheet sheet;
	private  Row row;
	private  List<ExcelExportHeader> headerList;
	private  List<Method> methodObj;
	private  Map<String, Method> convertMethod;
	private  int index = 0;

    private static Map<Class<?>,List<Field>> CACHE_EXCEL_ANNOTATION = Maps.newConcurrentMap();

	public  void initExcel(Class<?> pojoClass) throws Exception{
		// 声明一个工作薄
		workbook = new SXSSFWorkbook (1000);
		//获取表头
		getHeader(pojoClass);
	}

	public void createSheet(String sheetName) {
		 	index = 0;
	        sheet = workbook.createSheet(sheetName);
	        createHeader();
	        adjustColumnSize();
	 }


	private  void getHeader(Class<?> pojoClass) throws Exception{
		headerList = new ArrayList<ExcelExportHeader>();
		methodObj = new ArrayList<Method>();
		convertMethod = new HashMap<String, Method>();
		// 标题
		ExcelExportHeader header = null;
		// 得到所有字段
		List<Field> fileds = getAllFieldsWithExcelAnnotation(pojoClass);
		// 遍历整个filed
		for (Field field : fileds) {
			Excel excel = field.getAnnotation(Excel.class);
            header = new ExcelExportHeader();
            header.setExportFieldTitle(excel.exportName());
            header.setExportFieldWidth(excel.exportFieldWidth());
            headerList.add(header);

            String fieldname = field.getName();
            StringBuffer getMethodName = new StringBuffer("get");
            getMethodName.append(fieldname.substring(0, 1).toUpperCase());
            getMethodName.append(fieldname.substring(1));
            Method getMethod = pojoClass.getMethod(getMethodName.toString(), new Class[] {});
            methodObj.add(getMethod);
            if (excel.exportConvertSign() == 1) {
                StringBuffer getConvertMethodName = new StringBuffer("get");
                getConvertMethodName.append(fieldname.substring(0, 1).toUpperCase());
                getConvertMethodName.append(fieldname.substring(1));
                getConvertMethodName.append("Convert");
                Method getConvertMethod = pojoClass.getMethod(getConvertMethodName.toString(),new Class[] {});
                convertMethod.put(getMethodName.toString(),getConvertMethod);
            }
		}
	}

    private List<Field> getAllFieldsWithExcelAnnotation(Class<?> clazz) {
        if (CACHE_EXCEL_ANNOTATION.containsKey(clazz)) {
            return CACHE_EXCEL_ANNOTATION.get(clazz);
        }
        final List<Field> result = Lists.newArrayList();
        ReflectionUtils.doWithFields(clazz,new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                if (field.getAnnotation(Excel.class) != null) {
                    result.add(field);
                }
            }
        });
        CACHE_EXCEL_ANNOTATION.put(clazz,result);
        return result;
    }

	/**
	 * 创建一行，并把当前行加1
	 */
	private  void createRow() {
        row = sheet.createRow(index);
        index++;
	}


	private  void createHeader(){
		int length = headerList.size();
		ExcelExportHeader header = null;
		createRow();
		for (int i = 0;i < length; i++) {
			Cell cell = row.createCell(i);
			// cell.setCellStyle(style);
			header = headerList.get(i);
			RichTextString text = new HSSFRichTextString(header.getExportFieldTitle());
			cell.setCellValue(text);
		}
	}

	private  void adjustColumnSize(){
		// 设置每行的列宽
		int length = headerList.size();
		ExcelExportHeader header = null;
		for (int i = 0; i < length; i++) {
			// 256=65280/255
			header = headerList.get(i);
			sheet.setColumnWidth(i, 256 * header.getExportFieldWidth());
		}
    }


	public  void dealDate(Collection<?> dataSet) throws Exception{
		Iterator<?> its = dataSet.iterator();
		// 循环插入剩下的集合
		while (its.hasNext()) {
			// 从第二行开始写，第一行是标题
			createRow();
			Object t = its.next();
			for (int k = 0, methodObjSize = methodObj.size(); k < methodObjSize; k++) {
				Cell cell = row.createCell(k);
				Method getMethod = methodObj.get(k);
				Object value = null;
				if (convertMethod.containsKey(getMethod.getName())) {
					Method cm = convertMethod.get(getMethod.getName());
					value = cm.invoke(t, new Object[] {});
				} else {
					value = getMethod.invoke(t, new Object[] {});
				}
				cell.setCellValue(value==null?"":value.toString());
			}
		}

	}


	public SXSSFWorkbook  getWorkbook() {
		return workbook;
	}

	public void setWorkbook(SXSSFWorkbook  workbook) {
		this.workbook = workbook;
	}

	public Sheet getSheet() {
		return sheet;
	}

	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	public Row getRow() {
		return row;
	}

	public void setRow(Row row) {
		this.row = row;
	}

	public List<ExcelExportHeader> getHeaderList() {
		return headerList;
	}

	public void setHeaderList(List<ExcelExportHeader> headerList) {
		this.headerList = headerList;
	}

	public List<Method> getMethodObj() {
		return methodObj;
	}

	public void setMethodObj(List<Method> methodObj) {
		this.methodObj = methodObj;
	}

	public Map<String, Method> getConvertMethod() {
		return convertMethod;
	}

	public void setConvertMethod(Map<String, Method> convertMethod) {
		this.convertMethod = convertMethod;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
