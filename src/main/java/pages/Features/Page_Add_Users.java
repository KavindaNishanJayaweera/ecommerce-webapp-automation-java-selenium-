package pages.Features;

import com.base.ApplicationCommon;
import com.base.Core;
import io.cucumber.datatable.DataTable;

public class Page_Add_Users extends Core {

    public static void method_EnterTableData(DataTable dataTable) {
        ApplicationCommon.method_EnterTableData(dataTable); // Calls the common method to input table data.
    }
}
