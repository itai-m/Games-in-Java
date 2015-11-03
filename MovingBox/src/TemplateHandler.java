
public class TemplateHandler {

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
}
