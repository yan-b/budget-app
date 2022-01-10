package htw.berlin.budgetapp.service;

import htw.berlin.budgetapp.presistence.AccountEntryEntity;
import htw.berlin.budgetapp.presistence.AccountEntryRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static htw.berlin.budgetapp.presistence.EntryType.BELASTUNG;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountEntryServiceImplTest implements WithAssertions {

    @Mock
    private AccountEntryRepository repository;

    @InjectMocks
    private AccountEntryServiceImpl underTest;

    @Test
    void createEntry() {

        //given
        var entity = Mockito.mock(AccountEntryEntity.class);
        LocalDate date = LocalDate.of(2021, 6, 15);
        entity = new AccountEntryEntity(5L, "Bestellung Lieferando.de", BELASTUNG, -15.24, date, "111aaa");

        // when
        var result = underTest.createEntry(entity);

        // then
        assertThat(result.getUserFk()).isEqualTo("111aaa");
        assertThat(result.getEntryDescription()).isEqualTo("Bestellung Lieferando.de");
        assertThat(result.getEntryAmount()).isEqualTo(-15.24);
        assertThat(result.getAccountFk()).isEqualTo(5);
        assertThat(result.getEntryDate()).isEqualTo(date);
        assertThat(result.getEntryType()).isEqualTo(BELASTUNG);
    }

    @Test
    @DisplayName("Should return true if account does exist and gets deleted.")
    void deleteExistingEntryById() {

        // given
        Long givenId = 99999L;
        doReturn(true).when(repository).existsById(givenId);

        // when
        boolean result = underTest.deleteAccountEntryById(givenId);

        // then
        verify(repository).deleteById(givenId);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Should return false if account does not exist.")
    void deleteNotExistingEntryById() {

        // given
        Long givenId = 99999L;
        doReturn(false).when(repository).existsById(givenId);

        // when
        boolean result = underTest.deleteAccountEntryById(givenId);

        // then
        verifyNoMoreInteractions(repository);
        assertThat(result).isFalse();
    }

//    @Test
//    void findEntriesByAccountFk() {
//
//        //given
//        var entity = Mockito.mock(AccountEntryEntity.class);
//        LocalDate date = LocalDate.of(2021, 6, 15);
//
//        doReturn(9999L).when(entity).getAccountFk();
//        doReturn("Bestellung Lieferando.de").when(entity).getEntryDescription();
//        doReturn(BELASTUNG).when(entity).getEntryType();
//        doReturn(-15.24).when(entity).getEntryAmount();
//        doReturn(date).when(entity).getEntryDate();
//        doReturn("111aaa").when(entity).getUserFk();
//
//        // when
//        var result = repository.findByAccountFk(9999L);
//
//        // then
//        assertThat(result.get(0).getUserFk()).isEqualTo("111aaa");
//        assertThat(result.get(0).getEntryDescription()).isEqualTo("Bestellung Lieferando.de");
//        assertThat(result.get(0).getEntryAmount()).isEqualTo(-15.24);
//        assertThat(result.get(0).getAccountFk()).isEqualTo(9999);
//        assertThat(result.get(0).getEntryDate()).isEqualTo(date);
//        assertThat(result.get(0).getEntryType()).isEqualTo(BELASTUNG);
//
//    }

}