package no.nav.veilarbdemo.mock;

import no.nav.common.aktorregisterklient.AktorregisterKlient;
import no.nav.common.aktorregisterklient.IdentOppslag;

import java.util.List;
import java.util.Optional;

public class AktorregisterKlientMock implements AktorregisterKlient {

    @Override
    public Optional<String> hentFnr(String s) {
        return Optional.of("123456789");
    }

    @Override
    public Optional<String> hentAktorId(String s) {
        return Optional.empty();
    }

    @Override
    public List<IdentOppslag> hentFnr(List<String> list) {
        return null;
    }

    @Override
    public List<IdentOppslag> hentAktorId(List<String> list) {
        return null;
    }

}
