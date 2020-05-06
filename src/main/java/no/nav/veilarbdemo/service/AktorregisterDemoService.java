package no.nav.veilarbdemo.service;

import no.nav.common.abac.domain.AbacPersonId;
import no.nav.common.aktorregisterklient.AktorregisterKlient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AktorregisterDemoService {

    private final AktorregisterKlient aktorregisterKlient;

    private final TilgangService tilgangService;

    @Autowired
    public AktorregisterDemoService(AktorregisterKlient aktorregisterKlient, TilgangService tilgangService) {
        this.aktorregisterKlient = aktorregisterKlient;
        this.tilgangService = tilgangService;
    }

    public String hentFnr(String aktorId) {
        tilgangService.sjekkLesetilgangMedInnloggetBruker(AbacPersonId.aktorId(aktorId));
        return aktorregisterKlient.hentFnr(aktorId);
    }

    public String hentAktorId(String fnr) {
        tilgangService.sjekkLesetilgangMedInnloggetBruker(AbacPersonId.fnr(fnr));
        return aktorregisterKlient.hentAktorId(fnr);
    }

}
