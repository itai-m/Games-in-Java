
public class TemplateHandler {

	private final static String WIN_PATH =  System.getProperty("user.dir") + "\\image\\";
	private final static String LINUX_PATH =  System.getProperty("user.dir") + "/image/";
	
	public static String path;
	public static int template = 1;
	public static int maxTemplate = 1;
	

	///Set template level
	public static void setTemplate(int template) {
		TemplateHandler.template = template;
	}

	///Get template level
	public static int getTemplate() {
		return template;
	}
	
	///Increase by one the template level
	public static void incTemplate(){
		template = (template + 1) % maxTemplate;
	}
	
	///Check how much directory in the image directory
	public static void checkTemplateAmunt(){
		
	}
	
	///Initialization the path of the game
	public static void initPath(){
		if (System.getProperty("os.name").equals("Linux")){
			path = LINUX_PATH + TemplateHandler.getTemplate() + "/";
		}
		else{
			path = WIN_PATH + TemplateHandler.getTemplate() + "\\";
		}
	}
	
	///Get the path
	public static String getPath() {
		return path;
	}
}
