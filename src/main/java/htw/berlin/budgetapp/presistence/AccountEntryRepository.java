package htw.berlin.budgetapp.presistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountEntryRepository extends JpaRepository<AccountEntryEntity, Long> {

    List<AccountEntryEntity> findByAccountFk(Long accountFk);

}
