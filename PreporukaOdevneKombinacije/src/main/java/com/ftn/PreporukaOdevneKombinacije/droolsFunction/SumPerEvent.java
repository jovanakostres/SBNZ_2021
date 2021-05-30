package com.ftn.PreporukaOdevneKombinacije.droolsFunction;

import com.ftn.PreporukaOdevneKombinacije.model.drlModel.KoeficijentPreporuke;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SumPerEvent implements org.kie.api.runtime.rule.AccumulateFunction<SumPerEvent.SumPerItemData> {

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }

    public void writeExternal(ObjectOutput out) throws IOException {

    }

    public static class SumPerItemData implements Externalizable {
//        public int    count = 0;
//        public double total = 0;

        HashMap<Long, Double> tests = new HashMap<Long, Double>();


        public SumPerItemData() {}

        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            tests = (HashMap<Long, Double>) in.readObject();
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(tests);
        }

    }

    /* (non-Javadoc)
     * @see org.drools.base.accumulators.AccumulateFunction#createContext()
     */
    public SumPerItemData createContext() {
        return new SumPerItemData();
    }

    @Override
    public void init(SumPerItemData sumPerItemData) throws Exception {
        sumPerItemData.tests = new HashMap<Long, Double>();
    }

    @Override
    public void accumulate(SumPerItemData sumPerItemData, Object value) {
        SumPerItemData data = sumPerItemData;
        Double b = ((KoeficijentPreporuke)value).getBodovi() * ((KoeficijentPreporuke)value).getKomad().getKoeficijentOdabira();
        if (data.tests.get(((KoeficijentPreporuke)value).getKomad().getId()) == null) data.tests.put(((KoeficijentPreporuke)value).getKomad().getId(), b);
        else data.tests.put(((KoeficijentPreporuke)value).getKomad().getId(), data.tests.get(((KoeficijentPreporuke)value).getKomad().getId()) + b);
    }

    @Override
    public void reverse(SumPerItemData sumPerItemData, Object value) throws Exception {
        SumPerItemData data = sumPerItemData;
        Double b = ((KoeficijentPreporuke)value).getBodovi() * ((KoeficijentPreporuke)value).getKomad().getKoeficijentOdabira();
        data.tests.put(((KoeficijentPreporuke)value).getKomad().getId(), data.tests.get(((KoeficijentPreporuke)value).getKomad().getId()));
    }

    @Override
    public Object getResult(SumPerItemData sumPerItemData) throws Exception {
        SumPerItemData data = sumPerItemData;
        return new ArrayList<>(data.tests.values());
    }


    /* (non-Javadoc)
     * @see org.drools.core.base.accumulators.AccumulateFunction#supportsReverse()
     */
    public boolean supportsReverse() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public Class< ? > getResultType() {
        return ArrayList.class;
    }

}


