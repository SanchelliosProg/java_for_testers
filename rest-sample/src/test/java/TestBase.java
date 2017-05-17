import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Александр on 18.03.2017.
 */
public class TestBase {
    private PropertiesProvider props = new PropertiesProvider();
    public boolean isIssueOpen(int issueId) {
        RestAssured.authentication = RestAssured.basic(
                props.getProperty("bugify.login"),
                props.getProperty("bugify.password"));
        String json = RestAssured.get("http://demo.bugify.com/api/issues/"+ issueId +".json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");

        Set<Issue> is = new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
        Issue issue = is.iterator().next();
        if (issue.getState().equals("0") || issue.getState().equals("1")){
            return true;
        } else {
            return false;
        }
    }

    public void skipIfNotFixed(int issueId) {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
