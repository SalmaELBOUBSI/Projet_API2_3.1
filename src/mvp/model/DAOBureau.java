package mvp.model;

import Entreprise.Bureau;

import java.util.List;

public interface DAOBureau {

    Bureau addBureau(Bureau bureau);
    boolean removeBureau(Bureau bureau);
    Bureau updateBureau(Bureau bureau);
    Bureau readBureau(int id_bureau);
    List<Bureau> getBureaux();
}
