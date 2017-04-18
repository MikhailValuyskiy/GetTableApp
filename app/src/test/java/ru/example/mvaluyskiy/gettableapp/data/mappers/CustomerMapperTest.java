package ru.example.mvaluyskiy.gettableapp.data.mappers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.util.List;

import ru.example.mvaluyskiy.gettableapp.data.dto.CustomerDto;
import ru.example.mvaluyskiy.gettableapp.data.vo.Customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by m.valuyskiy on 18.04.17.
 */

public class CustomerMapperTest {

    public static final String CUSTOMER_JSON = "{\n" +
            "    \"customerFirstName\": \"Marilyn\",\n" +
            "    \"customerLastName\": \"Monroe\",\n" +
            "    \"id\": 0\n" +
            "  }";

    public static final String CUSTOMERS_LIST_JSON = "[\n" +
            "  {\n" +
            "    \"customerFirstName\": \"Marilyn\",\n" +
            "    \"customerLastName\": \"Monroe\",\n" +
            "    \"id\": 0\n" +
            "  },\n" +
            "  {\n" +
            "    \"customerFirstName\": \"Abraham\",\n" +
            "    \"customerLastName\": \"Lincoln\",\n" +
            "    \"id\": 1\n" +
            "  }\n" +
            "]";

    CustomerMapper mapper = new CustomerMapper();
    Gson gson = new Gson();

    @Test
    public void checkCustomerConvertion_isCorrect() throws Exception {
        CustomerDto dto = gson.fromJson(CUSTOMER_JSON, CustomerDto.class);
        assertNotNull("DTO is null", dto);

        Customer vo = mapper.getFromDto(dto);
        assertNotNull("VO is null", vo);

        checkCustomers(vo, dto);
    }

    private void checkCustomers(Customer vo, CustomerDto dto) {
        assertEquals(vo.getFirstName(), dto.getFirstName());
        assertEquals(vo.getLastName(), dto.getLastName());
        assertEquals(vo.getId(), dto.getId());
    }

    @Test
    public void checkCustomersConvertion_isCorrect() throws Exception {
        List<CustomerDto> dtos = gson.fromJson(CUSTOMERS_LIST_JSON, new TypeToken<List<CustomerDto>>() {
        }.getType());
        assertNotNull("DTO list is null", dtos);

        List<Customer> vos = mapper.getFromDtoCollection(dtos);
        assertNotNull("VO list is null", vos);

        assertEquals(dtos.size(), vos.size());

        for (int i = 0; i < dtos.size(); i++) {
            checkCustomers(vos.get(i), dtos.get(i));
        }
    }
}

