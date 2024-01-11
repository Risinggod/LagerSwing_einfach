import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mainview.MainViewController;
import service.DataAccessObject;

public class DataAccessObjectTest {

    private MainViewController mvcMock;
    private DataAccessObject dao;

    @BeforeEach
    void setUp() {
        dao = new DataAccessObject();
        mvcMock = mock(MainViewController.class);
        when(mvcMock.getListOfDTO()).thenReturn(dao.getAll());
    }

    @Test
    void listofdto_has_size_100() {
        assertEquals(100, mvcMock.getListOfDTO().size());
    }
}