package utilities;

public class FormatCheckRegex {
	public final static String NAME_REG = "[A-Z]{1}[a-z]*$";
	public final static String DATE_REG = "[0-9]{2}/[0-9]{2}/[0-9]{4}$";
	public final static String DATE1_REG = "[0-9]{4}-[0-9]{2}-[0-9]{2}$";
	public final static String ADDRESS_REG = "([A-Z]{1}[a-z]*\\s|[A-Z]{1}[a-z]*)|(\\s([A-Z]{1}[a-z]*|[a-z]*)),\\s[0-9]{2,3}," + "\\s([A-Z]{1}[a-z]*\\s|[A-Z]{1}[a-z]*)+," + "\\s([A-Z]{1}[a-z]*\\s|[A-Z]{1}[a-z]*)+$";
	public final static String PHONE_REG = "[0-9]+$";
	public final static String EMAIL_REG = "[a-z]+@[a-z]+\\.(com|ac\\.rs)$";
	public final static String INDEX_REG = "[a-z]{2}-[0-9]{3}-[0-9]{4}$";
	public final static String YOE_REG = "[0-9]{4}$";
	public final static String NUMBERS_REG = "[1-9][0-9]*";
}

