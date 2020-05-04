package no.nav.veilarbdemo.service;

import no.nav.common.abac.Pep;
import no.nav.common.abac.domain.AbacPersonId;
import no.nav.common.abac.domain.request.ActionId;
import no.nav.common.auth.subject.SsoToken;
import no.nav.common.auth.subject.SubjectHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class TilgangService {

    private final Pep veilarbPep;

    @Autowired
    public TilgangService(Pep veilarbPep) {
        this.veilarbPep = veilarbPep;
    }

    public void sjekkLesetilgangMedInnloggetBruker(AbacPersonId personId) {
        Optional<String> maybeToken = SubjectHandler.getSsoToken().map(SsoToken::getToken);
        veilarbPep.sjekkTilgangTilPerson(maybeToken.orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN)), ActionId.READ, personId);
    }

}
