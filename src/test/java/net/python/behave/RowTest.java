package net.python.behave;

import net.python.behave.json.BehaveFeature;
import net.python.behave.json.Row;
import org.junit.Before;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RowTest {

    ReportParser reportParser;
    Row row;

    @Before
    public void setUpJsonReports() throws IOException {
        List<String> jsonReports = new ArrayList<String>();
        jsonReports.add(FileReaderUtil.getAbsolutePathFromResource("net/python/behave/cells.json"));
        reportParser = new ReportParser(jsonReports);
        BehaveFeature feature = reportParser.getFeatures().entrySet().iterator().next().getValue().get(0);
        row = feature.getElements().get(0).getSteps().get(0).getRows()[0];
        feature.processSteps();
    }


    public void shouldReturnRows() {
        //assertThat(row, is(Row.class));
    }


    public void shouldReturnCells() {
       // assertThat(row.getCells()[0], is("title"));
       // assertThat(row.getCells()[1], is("lord"));
    }

}
