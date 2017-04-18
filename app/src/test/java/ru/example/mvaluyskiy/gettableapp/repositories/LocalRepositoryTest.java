package ru.example.mvaluyskiy.gettableapp.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

import ru.example.mvaluyskiy.gettableapp.data.database.OrmLiteDatabaseHelper;
import ru.example.mvaluyskiy.gettableapp.data.mappers.Utils;
import ru.example.mvaluyskiy.gettableapp.data.repository.LocalRepository;
import ru.example.mvaluyskiy.gettableapp.data.vo.Customer;
import ru.example.mvaluyskiy.gettableapp.data.vo.Table;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;

/**
 * Created by m.valuyskiy on 18.04.17.
 */


@RunWith(RobolectricTestRunner.class)
@Config(sdk = 21, manifest = Config.NONE)
public class LocalRepositoryTest {

    // The index is taken from TableMapperTest.TABLES_JSON
    // Where true means free table
    private static final int FREE_TABLE_INDEX = 1;

    LocalRepository localRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        localRepository = new LocalRepository(new OrmLiteDatabaseHelper(RuntimeEnvironment.application));
    }

    @Test
    public void checkSaveCustomers_isCorrect() throws Exception {
        List<Customer> customers = Utils.getDummyCustomers();
        localRepository.saveCustomers(customers);
        List<Customer> customersFromDb = localRepository.getCustomers();

        for (int i = 0; i < customersFromDb.size(); i++) {
            assertEquals(customers.get(i).getFirstName(), customersFromDb.get(i).getFirstName());
            assertEquals(customers.get(i).getLastName(), customersFromDb.get(i).getLastName());
            assertEquals(customers.get(i).getId(), customersFromDb.get(i).getId());
        }
    }

    @Test
    public void checkSaveTables_isCorrect() throws Exception {
        List<Table> tables = Utils.getDummyTables();
        localRepository.saveTables(tables);
        List<Table> tablesFromDb = localRepository.getTables();

        for (int i = 0; i < tablesFromDb.size(); i++) {
            assertEquals(tables.get(i).getId(), tablesFromDb.get(i).getId());
            assertEquals(tables.get(i).isFree(), tablesFromDb.get(i).isFree());
        }
    }

    @Test
    public void checkBookTable_isCorrect() throws Exception {
        List<Table> tablesList = Utils.getDummyTables();
        // Save tables
        localRepository.saveTables(tablesList);
        // Get free table
        Table freeTable = tablesList.get(FREE_TABLE_INDEX);

        // Book table
        Table bookedTable = new Table(freeTable.getId(), false);
        localRepository.bookTable(bookedTable);
        Table bookedTableFromDb = localRepository.getTables().get(FREE_TABLE_INDEX);

        // Check that statuses are different
        assertNotSame(freeTable.isFree(), bookedTableFromDb.isFree());
    }

    @Test
    public void checkClearReservations_isCorrect() throws Exception {
        List<Table> tableList = Utils.getDummyTables();
        localRepository.saveTables(tableList);
        localRepository.clearReservations();
        List<Table> freeTablesList = localRepository.getTables();

        for (int i = 0; i < freeTablesList.size(); i++) {
            assertEquals(freeTablesList.get(i).isFree(), true);
        }
    }
}