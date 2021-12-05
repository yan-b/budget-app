package htw.berlin.budgetapp.web;

import htw.berlin.budgetapp.config.ViewNames;
import htw.berlin.budgetapp.presistence.AccountEntryEntity;
import htw.berlin.budgetapp.presistence.AccountEntryRepository;
import htw.berlin.budgetapp.presistence.AccountRepository;
import htw.berlin.budgetapp.service.EntryType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

@Controller
public class EntriesController {
    private final AccountEntryRepository accountEntryRepository;

    public EntriesController(AccountEntryRepository accountEntryRepository) {
        this.accountEntryRepository = accountEntryRepository;
    }

    @GetMapping("/entries/id={accId}")
    public ModelAndView getEntryList(@AuthenticationPrincipal OidcUser user, @PathVariable String accId) {
        Map<String, Object> mav = new HashMap<String, Object>();
        Long accountId = Long.parseLong(accId);

        if (user != null) {
            String token = user.getSubject();
            mav.put("name", user.getGivenName() + " " + user.getFamilyName());
            ArrayList<AccountEntryEntity> entries = accountEntryRepository.findAllByAccountFk(accountId);
            Iterator<AccountEntryEntity> entriesIterator = entries.iterator();
            DecimalFormat df2 = new DecimalFormat("#.00");

            if (!entries.isEmpty()) {
                Double sumBal = 0.00;
                ArrayList<Double> sumList = new ArrayList<Double>();
                ArrayList<AccountEntryEntity> accountEntries = new ArrayList<AccountEntryEntity>();

                while (entriesIterator.hasNext()) {
                    AccountEntryEntity ent = entriesIterator.next();
                    accountEntries.add(ent);
                    Double acc_balance = ent.getEntryAmount();
                    sumList.add(acc_balance);
                }
                if (accountEntries.get(0).getUserFk().equals(token)) {
                    if (!sumList.isEmpty()) {
                        sumBal = sumList.stream().reduce(0.00, Double::sum);
                    } else {
                        sumBal = 0.00;
                    }

                    mav.put("balance", df2.format(sumBal).toString());
                    mav.put("entries", accountEntries);
                    return new ModelAndView(ViewNames.ENTRIES, mav);
                } else {
                    String str = "!!! NO ACCESS !!!";
                    mav.put("noAccess", str);
                    return new ModelAndView(ViewNames.ENTRIES, mav);
                }
            } else {
                String str = "You don't have any entries in this account yet!";
                mav.put("entriesEmpty", str);
                return new ModelAndView(ViewNames.ENTRIES, mav);
            }
            } else {
                String str = "!!! NO ACCESS !!!";
                mav.put("noAccess", str);
                return new ModelAndView(ViewNames.ENTRIES, mav);
            }
    }

    @PostMapping("/newEntrie/aid={aid}/desc={desc}/type={type}/amount={amount}")
    public ModelAndView createNewEntry(@AuthenticationPrincipal OidcUser user,
                                       @PathVariable String aid,
                                       @PathVariable String desc,
                                       @PathVariable String type,
                                       @PathVariable String amount) {

        String userId = user.getSubject();
        Long accountFk = Long.parseLong(aid);
        String description = desc;
        EntryType entryType = null;
        entryType.valueOf(type);
        Double entryAmount = Double.parseDouble(amount);
        LocalDate entryDate = LocalDate.now();

        AccountEntryEntity newAccountEntryEntity = new AccountEntryEntity(accountFk, description, entryType, entryAmount, entryDate, userId);
        accountEntryRepository.save(newAccountEntryEntity);

        return new ModelAndView("/entries/id="+ aid);
    }

    @GetMapping("/entries/delete/id={id}")
    public String deleteAccount(@AuthenticationPrincipal OidcUser user, @PathVariable String id) {
        Map<String, Object> mav = new HashMap<String, Object>();
        Long accountEntryId = Long.parseLong(id);
        Long aid = accountEntryRepository.findById(accountEntryId).get().getAccountFk();
        String accountId = Long.toString(aid);
        String uid = accountEntryRepository.findById(accountEntryId).get().getUserFk();


        if (uid.equals(user.getSubject())) {
            accountEntryRepository.deleteById(accountEntryId);
        }

        return "redirect:/entries/id=" + accountId;
    }
}