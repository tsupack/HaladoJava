package hu.me;

import hu.me.exceptions.NoMoneyInputException;
import hu.me.exceptions.NotEnoughMoneyException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class AppTest 
{
    private VonaljegyAutomataInterface AutomataInterfaceMock;
    private  VonaljegyAutomataHasznalat vonaljegyAutomataHasznalat;
    private Vasarlas vasarlas;

    {
        AutomataInterfaceMock = Mockito.mock(VonaljegyAutomataInterface.class);
        vonaljegyAutomataHasznalat = new VonaljegyAutomataHasznalat(AutomataInterfaceMock);
    }

    @Test
    public void automataHasznalat_whenVasarlasIsValid_thenReturnTrue() throws NotEnoughMoneyException, NoMoneyInputException {
        vasarlas = new Vasarlas();
        vasarlas.setDb(2);
        vasarlas.setFizetes(800);
        Mockito.when(AutomataInterfaceMock.penzBetolt(800)).thenReturn(true);
        Assert.assertTrue(vonaljegyAutomataHasznalat.vetel(vasarlas).isKiadva());
        Mockito.verify(AutomataInterfaceMock).penzBetolt(800);
    }

    @Test
    public void automataHasznalat_whenVasarlasIsNull_thenReturnFalse()  throws NotEnoughMoneyException, NoMoneyInputException  {
        vasarlas = null;

        Assert.assertFalse(vonaljegyAutomataHasznalat.vetel(vasarlas).isKiadva());
    }

    @Test
    public void automataHasznalat_whenMoneyIsNotEnough_thenReturnFalse() throws NotEnoughMoneyException, NoMoneyInputException {
        vasarlas = new Vasarlas();
        vasarlas.setDb(1);
        vasarlas.setFizetes(200);

        Assert.assertFalse(vonaljegyAutomataHasznalat.vetel(vasarlas).isKiadva());
    }

    @Test
    public void testPenzBetolt()  throws NotEnoughMoneyException, NoMoneyInputException {
        vasarlas = new Vasarlas();
        vasarlas.setDb(2);
        vasarlas.setFizetes(800);

        vonaljegyAutomataHasznalat.vetel(vasarlas);
        Mockito.verify(AutomataInterfaceMock).penzBetolt(800);
    }

    @Test
    public void automataHasznalat_whenDbIsNull_thenReturnFalse()  throws NotEnoughMoneyException, NoMoneyInputException {
        vasarlas = new Vasarlas();
        vasarlas.setFizetes(800);

        vonaljegyAutomataHasznalat.vetel(vasarlas);
        Assert.assertFalse(vonaljegyAutomataHasznalat.vetel(vasarlas).isKiadva());
    }

    @Test
    public void automataHasznalat_whenFizetesIsNull_thenReturnFalse()  throws NotEnoughMoneyException, NoMoneyInputException {
        vasarlas = new Vasarlas();
        vasarlas.setDb(5);

        vonaljegyAutomataHasznalat.vetel(vasarlas);
        Assert.assertFalse(vonaljegyAutomataHasznalat.vetel(vasarlas).isKiadva());
    }

}
