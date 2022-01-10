package htw.berlin.budgetapp.web;

import htw.berlin.budgetapp.presistence.AccountEntity;
import htw.berlin.budgetapp.service.AccountServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static htw.berlin.budgetapp.presistence.AccountType.GIROKONTO;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountRestController.class)
class AccountRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountServiceImpl accountService;

    @Test
    @DisplayName("should return found account from account service")
    void getAllAccounts() throws Exception {

        // given
        var accounts = List.of(
                new AccountEntity("111aaa", "Test 1", GIROKONTO, 180.45),
                new AccountEntity("111aaa", "Test 2", GIROKONTO, 14.78)
        );

        doReturn(accounts).when(accountService).findAllAccounts();

        // when
        mockMvc.perform(get("/api/v1/getAccounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].userId").value("111aaa"))
                .andExpect(jsonPath("$[0].accountName").value("Test 1"))
                .andExpect(jsonPath("$[0].accountType").value("GIROKONTO"))
                .andExpect(jsonPath("$[0].accountBalance").value("180.45"))
                .andExpect(jsonPath("$[1].userId").value("111aaa"))
                .andExpect(jsonPath("$[1].accountName").value("Test 2"))
                .andExpect(jsonPath("$[1].accountType").value("GIROKONTO"))
                .andExpect(jsonPath("$[1].accountBalance").value("14.78"));

    }

    @Test
    @DisplayName("should return 404 if account is not found")
    void accountNotFound() throws Exception {
        // given
        doReturn(null).when(accountService).findAccountById(anyLong());

        // when
        mockMvc.perform(get("/api/v1/getAccount/99999"))
                // then
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("should validate create account request")
    void createAccount() throws Exception {
        // given
        String personToCreateAsJson = "{\"userId\": \"a\", \"accountName\":\"\", \"accountType\":\"\", \"accountBalance\":}";

        // when
        mockMvc.perform(
                post("/api/v1/newAccount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personToCreateAsJson)
        )
                // then
                .andExpect(status().isBadRequest());
    }
}