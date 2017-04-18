package ru.example.mvaluyskiy.gettableapp.data.mappers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.util.List;

import ru.example.mvaluyskiy.gettableapp.data.vo.Table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by m.valuyskiy on 18.04.17.
 */

public class TableMapperTest {

    public static final String TABLES_JSON = "[false,true,false,false]";

    TableMapper mapper = new TableMapper();
    Gson gson = new Gson();

    @Test
    public void checkTablesConvertion_isCorrect() throws Exception {
        List<Boolean> tablesDto = gson.fromJson(TABLES_JSON, new TypeToken<List<Boolean>>() {
        }.getType());
        assertNotNull("DTO list is null", tablesDto);

        List<Table> tablesVo = mapper.getFromDtoCollection(tablesDto);
        assertNotNull("VO list is null", tablesVo);

        assertEquals(tablesDto.size(), tablesVo.size());
        for (int i = 0; i < tablesDto.size(); i++) {
            assertEquals(tablesDto.get(i), tablesVo.get(i).isFree());
        }
    }
}
