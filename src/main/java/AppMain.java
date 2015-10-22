import java.util.Scanner;


/**
 * Created by Paul on 10.09.15.
 */
public class AppMain {

    private AnimalDao animalDao;

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
        System.out.println("\nVelkommen til dyreavstemning, hvilket dyr er Norges nasjonaldyr?");
        System.out.println("----------------");
        System.out.println("Art.nr\tDyr");
        System.out.println("----------------");
    }

    // Choose a animal from the list.
    private void animalChooserMenu() {
        int choose;
        int stemmer = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n(Velg et av alternativene over: '1..6') ");
        System.out.println("(Trykk 0 -  for å avslutte");
        System.out.println("---------------------------------------");
        System.out.println("->>:");

        choose = scanner.nextInt();
        while (true) {

                switch (choose) {

                    case 0:
                        System.out.println("Program is quiting.\nGood bye!");
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
