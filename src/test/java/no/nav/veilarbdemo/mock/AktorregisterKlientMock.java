package no.nav.veilarbdemo.mock;

import no.nav.common.aktorregisterklient.AktorregisterKlient;
import no.nav.common.aktorregisterklient.IdentOppslag;

import java.util.List;

public class AktorregisterKlientMock implements AktorregisterKlient {

    @Override
    public String hentFnr(String s) {
        return "123456789";
    }

    @Override
    public String hentAktorId(String s) {
        return "00123456789";
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
