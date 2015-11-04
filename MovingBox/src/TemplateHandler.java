import java.io.File;
import java.io.FilenameFilter;


public class TemplateHandler {

	private final static String PRO_PATH = System.getProperty("user.dir");
	private final static String WIN_PATH =  PRO_PATH + "\\image\\";
	private final static String LINUX_PATH =  PRO_PATH + "/image/";
	private final static String TEMPLATE_NAME = "Template";
	
	public static String path;
	public static int template = 0;
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
	public static void checkTemplateAmunt(String tempPath){
		File file = new File(tempPath);
		String[] directories = file.list(new FilenameFilter() {
		  public boolean accept(File current, String name) {
			  if (name.length() < TEMPLATE_NAME.length())
				  return false;
			  if (name.substring(0, TEMPLATE_NAME.length()).equals(TEMPLATE_NAME)){
				  return new File(current, name).isDirectory();
			  }
			  else{
				  return false;
			  }
		  }
		});
		if (directories != null)
			maxTemplate = directories.length;
	}
	
	///Initialization the path of the game
	public static void initPath(){
		if (System.getProperty("os.name").equals("Linux")){
			path = LINUX_PATH + TEMPLATE_NAME + TemplateHandler.getTemplate() + "/";
			checkTemplateAmunt(LINUX_PATH);
		}
		else{
			path = WIN_PATH + TEMPLATE_NAME + TemplateHandler.getTemplate() + "\\";
			checkTemplateAmunt(WIN_PATH);
		}
	}
	
	///Get the path
	public static String getPath() {
		return path;
	}
}
