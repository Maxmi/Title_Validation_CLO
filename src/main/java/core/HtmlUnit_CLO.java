package core;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.gargoylesoftware.htmlunit.BrowserVersion;

@Parameters(separators="|")

public class HtmlUnit_CLO {
	
	public static final String sURL="-u";
	public static final String URL="--url";
	
	public static final String sTitle="-t";
	public static final String TitleExpected="--title";
	
	public static final String sTest_case_id="-c";
	public static final String Test_case_id="--tcase";
	
	public static final String sHelp="-h";
	public static final String Help="--help";
	
	
	@Parameter(names={sURL, URL}, description ="IRL of Web site -> http://.......")
	//Default value
	private static String url="http://learn2test.net/";

	@Parameter(names={sTitle, TitleExpected}, description ="Expected title of Web Site")
	//Default value
	//, required=true
	private static String title_expected="learn2test.net";	
	
	@Parameter(names={sTest_case_id, Test_case_id}, description ="Test case ID - > TC-xxx.xx")
	//Default value
	private static String test_case_id="TC-001.01";
	
	//Help
	@Parameter(names={sHelp, Help}, help=true, hidden=true)
	//Default value
	private static boolean help;
	
	public static void main(String yc[]) 
	{
		JCommander	CLO = new JCommander(new HtmlUnit_CLO(), yc);	
		if (help){CLO.usage();
		System.exit(0);}
		
		WebDriver driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_11);
		//Turning HtmlUnit Warnings off 
		//BEGIN		
				LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
		        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF); 
		        java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
		        java.util.logging.Logger.getLogger("org.apache.http.client.protocol.ResponseProcessCookies").setLevel(Level.OFF);
		//END
		        
		        driver.get(url);
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				String title_actual = driver.getTitle();
		
		
		
		if (title_expected.equals(title_actual)) {
			System.out.println();
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
	}
	
	
//END Class
}
