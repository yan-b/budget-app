package htw.berlin.budgetapp.service;

import htw.berlin.budgetapp.presistence.AccountEntity;
import htw.berlin.budgetapp.presistence.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import static htw.berlin.budgetapp.presistence.AccountType.GIROKONTO;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository repository;

    @InjectMocks
    private AccountServiceImpl underTest;

    @Test
    void createAccount() {

//        //given
//        var entity = Mockito.mock(AccountEntity.class);
//        entity = new AccountEntity("111aaa", "Testaccount Junit", GIROKONTO, 155.55);
//
//        // when
//        var result = underTest.createAccount(entity);
//
//        // then
//        assertThat(result.getUserId()).isEqualTo("111aaa");
//        assertThat(result.getAccountName()).isEqualTo("Testaccount Junit");
//        assertThat(result.getAccountType()).isEqualTo(GIROKONTO);
//        assertThat(result.getAccountBalance()).isEqualTo(155.55);
    }

    @Test
    void deleteAccountById() {
    }
}