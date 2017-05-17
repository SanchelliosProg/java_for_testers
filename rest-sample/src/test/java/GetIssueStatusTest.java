import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertTrue;


/**
 * Created by Александр on 17.05.2017.
 */
public class GetIssueStatusTest extends TestBase{
    @DataProvider
    public Iterator<Object[]> openedIssues(){
        List<Object[]> openIssuesIds = new ArrayList<>();
        openIssuesIds.add(new Object[]{1});
        openIssuesIds.add(new Object[]{3});
        openIssuesIds.add(new Object[]{9});
        return openIssuesIds.iterator();
    }

    @Test(dataProvider = "openedIssues")
    public void getIssue(Integer id){
        assertTrue(isIssueOpen(id));
    }
}
