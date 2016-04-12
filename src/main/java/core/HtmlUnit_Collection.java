package core;


//import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
//import com.beust.jcommander.Parameters;
import com.gargoylesoftware.htmlunit.BrowserVersion;

//@Parameters(separators="|")

public class HtmlUnit_Collection {

	//CLO
	public static final String sLIST="-l";
	public static final String LIST="--list";
	public static final String sHelp="-h";
	public static final String Help="--help";
	
	//@Parameter(names={sLIST, LIST},arity = 4, description ="2 Pairs of IRL & title.  multiple")
	//private static List<String> list;
	@Parameter(names={sLIST, LIST}, variableArity = true, description ="Description: Pairs of IRL & title. Separators of Pairs is \"|\". Example: \"http://www.youtube.com|YouTube\" ")
	public static List<String> list;
	//= new ArrayList<>();
	
	//Help
	@Parameter(names={sHelp, Help}, help=true, hidden=true)
	//Default value
	private static boolean help;
	
	
	public static void main(String yc[]) 
	{
	
		JCommander	CLO = new JCommander(new HtmlUnit_Collection(), yc);	
		if (help)
		{
			CLO.usage();
		System.exit(0);
		}
		
		WebDriver driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_11);

		//Turning HtmlUnit Warnings off 
		//BEGIN		
				LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
		        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF); 
		        java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
		        java.util.logging.Logger.getLogger("org.apache.http.client.protocol.ResponseProcessCookies").setLevel(Level.OFF);
		//END
		
		
		
		for(int i=0;i<list.size();i++)
		{
			String test_case_id = "TC-002.0"+(i+1);
			String param[] = list.get(i).split("\\|");
			String url = param[0];
			String title_expected = param[1];
			
			
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			String title_actual = driver.getTitle();

			if (title_expected.equals(title_actual)) {
				System.out.println(">>>>....");
				System.out.println("Test Case ID: \t\t" + test_case_id);
				System.out.println("URL: \t\t\t" + url);
				System.out.println("Title Expected: \t" + title_expected);
				System.out.println("Title Actual: \t\t" + title_actual);
				System.out.println("Test Case Result: \t" + "PASSED");
				System.out.println("....<<<<");
				System.out.println();
			} else {
				System.out.println(">>>>....");
				System.out.println("Test Case ID: \t\t" + test_case_id);
				System.out.println("URL: \t\t\t" + url);
				System.out.println("Title Expected: \t" + title_expected);
				System.out.println("Title Actual: \t\t" + title_actual);
				System.out.println("Test Case Result: \t" + "FAILED");
				System.out.println("....<<<<");
				System.out.println();
			}
			
			
			 
			//Loop for
			
		}
	driver.quit();
		//System.out.println(list.toString());
		//System.out.println(list.size());
		//System.out.println(list.get(0));
		
		}

	
	
}
