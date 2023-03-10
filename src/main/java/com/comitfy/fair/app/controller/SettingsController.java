package com.comitfy.fair.app.controller;

import com.comitfy.fair.app.dto.SettingsDTO;
import com.comitfy.fair.app.dto.requestDTO.SettingsRequestDTO;
import com.comitfy.fair.app.entity.Settings;
import com.comitfy.fair.app.mapper.SettingsMapper;
import com.comitfy.fair.app.repository.SettingsRepository;
import com.comitfy.fair.app.service.SettingsService;
import com.comitfy.fair.app.specification.SettingsSpecification;
import com.comitfy.fair.userModule.entity.User;
import com.comitfy.fair.util.common.BaseCrudController;
import com.comitfy.fair.util.common.HelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("settings")
public class SettingsController extends BaseCrudController<SettingsDTO, SettingsRequestDTO, Settings, SettingsRepository, SettingsMapper, SettingsSpecification, SettingsService> {

    @Autowired
    SettingsMapper settingsMapper;

    @Autowired
    SettingsService settingsService;

    @Autowired
    HelperService helperService;

    @Override
    protected SettingsService getService() {
        return settingsService;
    }

    @Override
    protected SettingsMapper getMapper() {
        return settingsMapper;
    }

    @PostMapping("/user-api")
    public ResponseEntity<SettingsRequestDTO> saveByUserId(@RequestHeader(value = "accept-language", required = true) String acceptLanguage,
                                                           @RequestBody SettingsRequestDTO settingsRequestDTO) {
        User user = helperService.getUserFromSession();
        if (user != null) {
            {
                return new ResponseEntity<>(settingsService.saveSettingsByUser(user.getUuid(), settingsRequestDTO), HttpStatus.OK);
            }
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/user-api/{settingsId}")
    public ResponseEntity<String> updateSettings(@PathVariable UUID settingsId, @RequestBody SettingsRequestDTO dto) {
        User user = helperService.getUserFromSession();
        SettingsDTO settingsDTO = settingsService.findByUUID(settingsId);

        if (settingsDTO == null || user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        } else {
            settingsService.updateSettings(settingsId, dto, user);
            //   dto.setLanguageEnum(LanguageEnum.valueOf(categoryDTO.getLanguage()));
            return new ResponseEntity<>("The object was updated.", HttpStatus.OK);
        }

    }


    @PostMapping("/city-api")
    public ResponseEntity<List<String>> getCities() {

        String[] sehirler = {"Adana", "Ad??yaman", "Afyon", "A??r??", "Amasya", "Ankara", "Antalya", "Artvin",
                "Ayd??n", "Bal??kesir", "Bilecik", "Bing??l", "Bitlis", "Bolu", "Burdur", "Bursa", "??anakkale",
                "??ank??r??", "??orum", "Denizli", "Diyarbak??r", "Edirne", "Elaz????", "Erzincan", "Erzurum ", "Eski??ehir",
                "Gaziantep", "Giresun", "G??m????hane", "Hakkari", "Hatay", "Isparta", "Mersin", "??stanbul", "??zmir",
                "Kars", "Kastamonu", "Kayseri", "K??rklareli", "K??r??ehir", "Kocaeli", "Konya", "K??tahya ", "Malatya",
                "Manisa", "Kahramanmara??", "Mardin", "Mu??la", "Mu??", "Nev??ehir", "Ni??de", "Ordu", "Rize", "Sakarya",
                "Samsun", "Siirt", "Sinop", "Sivas", "Tekirda??", "Tokat", "Trabzon  ", "Tunceli", "??anl??urfa", "U??ak",
                "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt ", "Karaman", "K??r??kkale", "Batman", "????rnak",
                "Bart??n", "Ardahan", "I??d??r", "Yalova", "Karab??k ", "Kilis", "Osmaniye ", "D??zce"};

        return new ResponseEntity<>(Arrays.asList(sehirler), HttpStatus.OK);


    }


}
