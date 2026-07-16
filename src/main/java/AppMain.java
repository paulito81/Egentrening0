import domain.AnimalMenu;
import domain.AnimalVoteService;
import infrastructure.AnimalDao;


/**
 * Created by Paul on 10.09.15.
 */
void main() {
    AnimalDao animalDao = new AnimalDao();
    AnimalVoteService voteService = new AnimalVoteService(animalDao);
    AnimalMenu menu = new AnimalMenu(voteService);

    menu.run();
}