package htw.berlin.budgetapp.presistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountEntryRepository extends JpaRepository<AccountEntryEntity, Long> {

//    ArrayList<AccountEntryEntity> findAllByAccountFk(Long accountFk);
//
//    void deleteAllByAccountFk(Long accountFk);

}
