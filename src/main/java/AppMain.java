import domain.AnimalVoteService;
import infrastructure.AnimalDao;
import model.AnimalType;

import java.util.Scanner;
import java.util.logging.Logger;


/**
 * Created by Paul on 10.09.15.
 */
public class AppMain {

    private AnimalDao animalDao;
    private Logger logger = Logger.getLogger(AppMain.class.getName());

    public static void main(String[] args) {

        new AppMain();
    }
    AppMain(){

        animalDao = new AnimalDao();
        preMenu();
        new AnimalVoteService();
        animalChooserMenu();

    }
    private void preMenu(){
       logger.info("\nVelkommen til dyreavstemning, hvilket dyr er Norges nasjonaldyr?");
        logger.info("----------------");
        logger.info("Art.nr\tDyr");
        logger.info("----------------");
    }

    // Choose a animal from the list.
    private void animalChooserMenu() {
        int choose;
        int stemmer = 0;
        Scanner scanner = new Scanner(System.in);

        logger.info("\n(Velg et av alternativene over: '1..6') ");
        logger.info("(Trykk 0 -  for å avslutte");
        logger.info("---------------------------------------");
        logger.info("->>:");

        choose = scanner.nextInt();
        while (true) {

                switch (choose) {

                    case 0:
                        logger.info("Program is quiting.\nGood bye!");
                        System.exit(0);
                        break;
                    case 1:

                        stemmer++;
                        animalDao.updateSQL(AnimalType.GREVLING, stemmer);

                        break;
                    case 2:
                        stemmer++;
                        animalDao.updateSQL(AnimalType.LEMMEN, stemmer);
                        break;
                    case 3:
                        stemmer++;
                        animalDao.updateSQL(AnimalType.HARE, stemmer);
                        break;
                    case 4:
                        stemmer++;
                        animalDao.updateSQL(AnimalType.ELG, stemmer);
                        break;
                    case 5:
                        stemmer++;
                        animalDao.updateSQL(AnimalType.SILD, stemmer);
                        break;
                    case 6:
                        stemmer++;
                        animalDao.updateSQL(AnimalType.MARKMUS, stemmer);
                        break;
                    default:
                        System.err.println("En feil er registrert! Velg 'et' av valgene over ( 1 --> 6)");
                        break;
                }
            }
    }
}
