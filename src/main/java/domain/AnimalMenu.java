package domain;

import model.AnimalType;

import java.util.Scanner;
import java.util.logging.Logger;

public class AnimalMenu {

    private final AnimalVoteService voteService;
    private final Logger logger = Logger.getLogger(AnimalMenu.class.getName());

    public AnimalMenu(AnimalVoteService voteService) {
        this.voteService = voteService;
    }

    public void run() {
        preMenu();
        animalChooserMenu();
    }

    private void preMenu() {
        logger.info("\nVelkommen til dyreavstemning, hvilket dyr er Norges nasjonaldyr?");
        logger.info("----------------");
        logger.info("Art.nr\tDyr");
        logger.info("----------------");
        // her kan du eventuelt skrive ut lista fra AnimalType eller AnimalDao
    }

    private void animalChooserMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            logger.info("\n(Velg et av alternativene over: '1..6')");
            logger.info("(Trykk 0 for å avslutte)");
            logger.info("---------------------------------------");
            logger.info("->>:");

            int choose = scanner.nextInt();

            switch (choose) {
                case 0:
                    logger.info("Program avsluttes.\nHa det!");
                    System.exit(0);
                    break;
                case 1:
                    voteService.registrerStemme(AnimalType.GREVLING);
                    break;
                case 2:
                    voteService.registrerStemme(AnimalType.LEMMEN);
                    break;
                case 3:
                    voteService.registrerStemme(AnimalType.HARE);
                    break;
                case 4:
                    voteService.registrerStemme(AnimalType.ELG);
                    break;
                case 5:
                    voteService.registrerStemme(AnimalType.SILD);
                    break;
                case 6:
                    voteService.registrerStemme(AnimalType.MARKMUS);
                    break;
                default:
                    logger.warning("En feil er registrert. Velg et av valgene over (1–6).");
                    break;
            }
        }
    }
}
