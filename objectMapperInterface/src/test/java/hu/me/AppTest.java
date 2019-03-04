package hu.me;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import hu.me.exceptions.DivisionByZeroException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void give2Strings_AreEqual_ThenCorrect() {
        String a = "foo";
        String b = "FOO";
        assertThat(a, equalToIgnoringCase(b));
    }

    @Test
    public void szerviz_amikorSzoroz_akkorErvenyesHibakod() {
        Input input = new Input();
        input.setMuvelet("szoroz");
        double[] operandusok = new double[2];
        operandusok[0] = 3;
        operandusok[1] = 4;
        input.setOperandusok(operandusok);

        SzamologepInterface szamologepInterface = mock(Szamologep.class);
        Mockito.when(szamologepInterface.szorzas(3, 4)).thenReturn(12.);
        Szerviz szerviz = new Szerviz(szamologepInterface);

        Output output = szerviz.szamol(input);

        Assert.assertThat(output.getEredmeny(), is(12.));
        Mockito.verify(szamologepInterface).szorzas(3, 4);
    }

    @Test
    public void szerviz_amikorOsszead_akkorErvenyesHibakod() {
        Input input = new Input();
        input.setMuvelet("osszead");
        double[] operandusok = new double[2];
        operandusok[0] = 5;
        operandusok[1] = 5;
        input.setOperandusok(operandusok);

        SzamologepInterface szamologepInterface = mock(Szamologep.class);
        Mockito.when(szamologepInterface.osszeadas(5, 5)).thenReturn(10.);
        Szerviz szerviz = new Szerviz(szamologepInterface);

        Output output = szerviz.szamol(input);

        Assert.assertThat(output.getEredmeny(), is(10.));
        Mockito.verify(szamologepInterface).osszeadas(5, 5);
    }

    @Test
    public void szerviz_amikorKivon_akkorErvenyesHibakod() {
        Input input = new Input();
        input.setMuvelet("kivon");
        double[] operandusok = new double[2];
        operandusok[0] = 6;
        operandusok[1] = 3;
        input.setOperandusok(operandusok);

        SzamologepInterface szamologepInterface = mock(Szamologep.class);
        Mockito.when(szamologepInterface.kivonas(6, 3)).thenReturn(3.);
        Szerviz szerviz = new Szerviz(szamologepInterface);

        Output output = szerviz.szamol(input);

        Assert.assertThat(output.getEredmeny(), is(3.));
        Mockito.verify(szamologepInterface).kivonas(6, 3);
    }

    @Test
    public void szerviz_amikorOszt_akkorErvenyesHibakod() throws DivisionByZeroException {
        Input input = new Input();
        input.setMuvelet("oszt");
        double[] operandusok = new double[2];
        operandusok[0] = 20;
        operandusok[1] = 4;
        input.setOperandusok(operandusok);

        SzamologepInterface szamologepInterface = mock(Szamologep.class);
        Mockito.when(szamologepInterface.osztas(20, 4)).thenReturn(5.);
        Szerviz szerviz = new Szerviz(szamologepInterface);

        Output output = szerviz.szamol(input);

        Assert.assertThat(output.getEredmeny(), is(5.));
        Mockito.verify(szamologepInterface).osztas(20, 4);
    }

    @Test
    public void szerviz_amikorOsztMuveletNagybetus_akkorErvenyesHibakod() throws DivisionByZeroException {
        Input input = new Input();
        input.setMuvelet("OSZT");
        double[] operandusok = new double[2];
        operandusok[0] = 20;
        operandusok[1] = 4;
        input.setOperandusok(operandusok);

        SzamologepInterface szamologepInterface = mock(Szamologep.class);
        Mockito.when(szamologepInterface.osztas(20, 4)).thenReturn(5.);
        Szerviz szerviz = new Szerviz(szamologepInterface);

        Output output = szerviz.szamol(input);

        Assert.assertThat(output.getEredmeny(), is(5.));
        Mockito.verify(szamologepInterface).osztas(20, 4);
    }

    @Test
    public void szerviz_amikorKivonMuveletNagybetus_akkorErvenyesHibakod() {
        Input input = new Input();
        input.setMuvelet("KIVON");
        double[] operandusok = new double[2];
        operandusok[0] = 6;
        operandusok[1] = 3;
        input.setOperandusok(operandusok);

        SzamologepInterface szamologepInterface = mock(Szamologep.class);
        Mockito.when(szamologepInterface.kivonas(6, 3)).thenReturn(3.);
        Szerviz szerviz = new Szerviz(szamologepInterface);

        Output output = szerviz.szamol(input);

        Assert.assertThat(output.getEredmeny(), is(3.));
        Mockito.verify(szamologepInterface).kivonas(6, 3);
    }

    @Test
    public void szerviz_amikorOsszeadMuveletNagybetus_akkorErvenyesHibakod() {
        Input input = new Input();
        input.setMuvelet("OSSZEAD");
        double[] operandusok = new double[2];
        operandusok[0] = 5;
        operandusok[1] = 5;
        input.setOperandusok(operandusok);

        SzamologepInterface szamologepInterface = mock(Szamologep.class);
        Mockito.when(szamologepInterface.osszeadas(5, 5)).thenReturn(10.);
        Szerviz szerviz = new Szerviz(szamologepInterface);

        Output output = szerviz.szamol(input);

        Assert.assertThat(output.getEredmeny(), is(10.));
        Mockito.verify(szamologepInterface).osszeadas(5, 5);
    }

    @Test
    public void szerviz_amikorSzorozMuveletNagybetus_akkorErvenyesHibakod() {
        Input input = new Input();
        input.setMuvelet("SZOROZ");
        double[] operandusok = new double[2];
        operandusok[0] = 3;
        operandusok[1] = 4;
        input.setOperandusok(operandusok);

        SzamologepInterface szamologepInterface = mock(Szamologep.class);
        Mockito.when(szamologepInterface.szorzas(3, 4)).thenReturn(12.);
        Szerviz szerviz = new Szerviz(szamologepInterface);

        Output output = szerviz.szamol(input);

        Assert.assertThat(output.getEredmeny(), is(12.));
        Mockito.verify(szamologepInterface).szorzas(3, 4);
    }

    @Test
    public void szerviz_amikorNullInput_akkorErvenytelenmuveletHibakod() {
        Input input = null;
        SzamologepInterface szamologepInterface = mock(SzamologepInterface.class);
        Szerviz szerviz = new Szerviz(szamologepInterface);
        Assert.assertThat(szerviz.szamol(input).getHibakod(), is(Hibakod.ERVENYTELENMUVELET));
    }

    @Test
    public void szerviz_amikorUresMuveletEsOperandusok_akkorErvenytelenmuveletHibakod() {
        Input input = new Input();
        input.setMuvelet("");
        SzamologepInterface szamologepInterface = mock(SzamologepInterface.class);
        Szerviz szerviz = new Szerviz(szamologepInterface);
        Assert.assertThat(szerviz.szamol(input).getHibakod(), is(Hibakod.ERVENYTELENMUVELET));
    }

    @Test
    public void szerviz_amikorElirtMuveletEsUresOperandusok_akkorErvenytelenmuveletHibakod() {
        Input input = new Input();
        input.setMuvelet("oztas");
        SzamologepInterface szamologepInterface = mock(SzamologepInterface.class);
        Szerviz szerviz = new Szerviz(szamologepInterface);
        Assert.assertThat(szerviz.szamol(input).getHibakod(), is(Hibakod.ERVENYTELENMUVELET));
    }

    @Test
    public void szerviz_amikorElirtMuveletDeErvenyesOperandusok_akkorErvenytelenmuveletHibakod() {
        Input input = new Input();
        double[] operandusok = new double[2];
        operandusok[0] = 3;
        operandusok[1] = 4;
        input.setOperandusok(operandusok);
        input.setMuvelet("oztas");

        SzamologepInterface szamologepInterface = mock(SzamologepInterface.class);
        Szerviz szerviz = new Szerviz(szamologepInterface);
        Assert.assertThat(szerviz.szamol(input).getHibakod(), is(Hibakod.ERVENYTELENMUVELET));
    }

    @Test
    public void szerviz_amikorErvenyesekAzOperandusokDeUresMuvelet_akkorErvenytelenmuveletHibakod() {
        double[] operandusok = new double[2];
        operandusok[0] = 3;
        operandusok[1] = 4;
        Input input = new Input();
        input.setOperandusok(operandusok);
        input.setMuvelet("");

        SzamologepInterface szamologepInterface = mock(Szamologep.class);
        Szerviz szerviz = new Szerviz(szamologepInterface);

        Assert.assertThat(szerviz.szamol(input).getHibakod(), is(Hibakod.ERVENYTELENMUVELET));
    }

    @Test
    public void szerviz_amikorElsoOperndusErvenyesTobbiUres_akkorErvenytelenmuveletHibakod() {
        double[] operandusok = new double[2];
        operandusok[0] = 3;

        Input input = new Input();
        input.setMuvelet("");
        input.setOperandusok(operandusok);
        SzamologepInterface szamologepInterface = mock(SzamologepInterface.class);
        Szerviz szerviz = new Szerviz(szamologepInterface);
        Assert.assertThat(szerviz.szamol(input).getHibakod(), is(Hibakod.ERVENYTELENMUVELET));
    }

    @Test
    public void szerviz_amikorMasodikOperndusErvenyesTobbiUres_akkorErvenytelenmuveletHibakod() {
        double[] operandusok = new double[2];
        operandusok[1] = 3;

        Input input = new Input();
        input.setMuvelet("");
        input.setOperandusok(operandusok);
        SzamologepInterface szamologepInterface = mock(SzamologepInterface.class);
        Szerviz szerviz = new Szerviz(szamologepInterface);
        Assert.assertThat(szerviz.szamol(input).getHibakod(), is(Hibakod.ERVENYTELENMUVELET));
    }

    @Test
    public void szerviz_amikorUresOprandusokDeErvenyesMuvelet_akkorErvenytelenoperandusHibakod() {
        Input input = new Input();
        input.setMuvelet("kivon");
        SzamologepInterface szamologepInterface = mock(SzamologepInterface.class);
        Szerviz szerviz = new Szerviz(szamologepInterface);
        Assert.assertThat(szerviz.szamol(input).getHibakod(), is(Hibakod.ERVENYTELENOPREANDUS));
    }

    @Test
    public void szerviz_amikorElsoOperandusNullDeTobbiErvenyes_akkorErvenytelenoperandusHibakod() {
        double[] operandusok = new double[2];
        operandusok[1] = 3;
        Input input = new Input();
        input.setMuvelet("szoroz");
        input.setOperandusok(operandusok);
        SzamologepInterface szamologepInterface = mock(SzamologepInterface.class);
        Szerviz szerviz = new Szerviz(szamologepInterface);
        Assert.assertThat(szerviz.szamol(input).getHibakod(), is(Hibakod.ERVENYTELENOPREANDUS));
    }

    @Test
    public void szerviz_amikorMasodikOperandusNullDeTobbiErvenyes_akkorErvenytelenoperandusHibakod() {
        double[] operandusok = new double[2];
        operandusok[0] = 3;
        Input input = new Input();
        input.setMuvelet("szoroz");
        input.setOperandusok(operandusok);
        SzamologepInterface szamologepInterface = mock(SzamologepInterface.class);
        Szerviz szerviz = new Szerviz(szamologepInterface);
        Assert.assertThat(szerviz.szamol(input).getHibakod(), is(Hibakod.ERVENYTELENOPREANDUS));
    }

    @Test
    public void szerviz_amikorMasodikOperandusNullaOsztas_akkorNullavalvaloosztasHibakod() {
        double[] operandusok = new double[2];
        operandusok[0] = 3;
        operandusok[1] = 0;
        Input input = new Input();
        input.setMuvelet("osztas");
        input.setOperandusok(operandusok);
        SzamologepInterface szamologepInterface = mock(SzamologepInterface.class);
        Szerviz szerviz = new Szerviz(szamologepInterface);
        Assert.assertThat(szerviz.szamol(input).getHibakod(), is(Hibakod.NULLAVALVALOOSZTAS));
    }

    @Test
    public void szerviz_amikorElsoOperandusNullaOsztas_akkorErvnyesHibakod() {
        double[] operandusok = new double[2];
        operandusok[1] = 3;
        operandusok[0] = 0;
        Input input = new Input();
        input.setMuvelet("osztas");
        input.setOperandusok(operandusok);
        SzamologepInterface szamologepInterface = mock(SzamologepInterface.class);
        Szerviz szerviz = new Szerviz(szamologepInterface);
        Assert.assertThat(szerviz.szamol(input).getHibakod(), is(Hibakod.ERVENYES));
    }
}
