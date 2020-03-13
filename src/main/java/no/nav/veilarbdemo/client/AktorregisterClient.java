package no.nav.veilarbdemo.client;

import no.nav.common.aktorregisterklient.AktorregisterKlient;
import no.nav.common.aktorregisterklient.IdentOppslag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AktorregisterClient implements AktorregisterKlient {

    private final AktorregisterKlient aktorregisterKlient;

    @Autowired
    public AktorregisterClient(AktorregisterKlient aktorregisterKlient) {
        this.aktorregisterKlient = aktorregisterKlient;
    }

    @Override
    public Optional<String> hentFnr(String aktorId) {
        return aktorregisterKlient.hentFnr(aktorId);
    }

    @Override
    public Optional<String> hentAktorId(String fnr) {
        return Optional.empty();
    }

    @Override
    public List<IdentOppslag> hentFnr(List<String> aktorIdList) {
        return null;
    }

    @Override
    public List<IdentOppslag> hentAktorId(List<String> fnrList) {
        return null;
    }

}
