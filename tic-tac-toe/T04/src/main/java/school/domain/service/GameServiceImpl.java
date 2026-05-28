package school.domain.service;

import school.datasource.repository.GameRepository;
import school.datasource.repository.GameRepositoryImpl;
import school.domain.model.Board;
import school.domain.model.Game;
import school.domain.model.GameStatus;
import school.domain.model.Move;

import java.util.UUID;

public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository){
        if (gameRepository!=null){
            this.gameRepository=gameRepository;
        }
        else {
            throw new IllegalArgumentException("Incorrect. GameRepository is null");
        }
    }

    @Override
    public Move findBestMove(Game game) {
        /// -1 Плохой ход для пк ведет к победе игрока, 0 ведет к ничьей и 1 ведет к победе пк.
        if (game == null) {
            throw new IllegalArgumentException("Game cannot be null");
        }
        if (getGameStatus(game) != GameStatus.IN_PROGRESS) {
            throw new IllegalArgumentException("Game is not in progress");
        }
        Board board = game.getBoard();
        int bestScore = -2;
        Move bestMove = null;
        for (int i = 0; i < Board.row; i++) {
            for (int j = 0; j < Board.col; j++) {
                if (board.isEmptyCell(i, j)) {
                    board.setCellState(i, j, Board.computer);
                    int score = minimax(board, false);
                    board.setCellState(i, j, Board.empty);
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new Move(i, j);
                    }
                }
            }
        }
        return bestMove;
    }

    private int minimax(Board board, boolean isComputerTurn) {
        /**
         Метод для вычисления лучшего хода для пк. максимальное число является лучшим
         */
        GameStatus status=getBoardStatus(board);
        switch (status){
            case DRAW:
                return 0;
            case COMPUTER_WIN:
                return 1;
            case PLAYER_WIN:
                return -1;
            case IN_PROGRESS:
                break;
        }
        if (isComputerTurn){
            int bestScore=-2;
            for (int i=0;i<Board.row;i++){
                for (int j=0;j<Board.col;j++){
                    if(board.isEmptyCell(i,j)){
                        board.setCellState(i,j,Board.computer);
                        int score=minimax(board,false);
                        board.setCellState(i,j,Board.empty);
                        if(score>bestScore){
                            bestScore=score;
                        }
                    }
                }
            }
            return bestScore;
        }
        else {
            int bestScore=2;
            for (int i=0;i<Board.row;i++){
                for (int j=0;j<Board.col;j++){
                    if (board.isEmptyCell(i,j)){
                        board.setCellState(i,j,Board.player);
                        int score=minimax(board,true);
                        board.setCellState(i,j,Board.empty);
                        if(score<bestScore){
                            bestScore=score;
                        }
                    }
                }
            }
            return bestScore;
        }
    }


    private void checkGamesNotNull(Game pastGame, Game currentGame) {
        if (pastGame == null) {
            throw new IllegalArgumentException("Past game cannot be null");
        }
        if (currentGame == null) {
            throw new IllegalArgumentException("Current game cannot be null");
        }
        if (pastGame.getBoard() == null) {
            throw new IllegalArgumentException("Past game board cannot be null");
        }
        if (currentGame.getBoard() == null) {
            throw new IllegalArgumentException("Current game board cannot be null");
        }
    }

    private boolean checkPlayerMoveCount(Game pastGame, Game currentGame) {
        /**
         Метод осуществляет проверку, что пользователь сделал ровно один ход
         */
        int userMotion = 0;
        for (int i = 0; i < Board.row; i++) {
            for (int j = 0; j < Board.col; j++) {
                if (pastGame.getBoard().isEmptyCell(i, j)) {
                    if (!currentGame.getBoard().isEmptyCell(i, j)) {
                        if (currentGame.getBoard().getCellState(i, j) != Board.player) {
                            return false;
                        } else {
                            userMotion++;
                        }
                    }
                }
            }
        }
        if (userMotion == 0 || userMotion > 1) {
            return false;
        }
        return true;
    }

    private boolean checkPastCellsIntegrity(Game pastGame, Game currentGame) {
        /**
         Метод проверки что значение в клетке не было замененно
         */
        for (int i = 0; i < Board.row; i++) {
            for (int j = 0; j < Board.col; j++) {
                if (pastGame.getBoard().getCellState(i, j) != currentGame.getBoard().getCellState(i, j) && !pastGame.getBoard().isEmptyCell(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean validateGame(Game pastGame, Game currentGame) {
        boolean flag = true;
        checkGamesNotNull(pastGame, currentGame);
        if (!pastGame.getId().equals(currentGame.getId())) {
            return false;
        }
        if (getGameStatus(pastGame) != GameStatus.IN_PROGRESS) {
            return false;
        }
        if (!checkPastCellsIntegrity(pastGame, currentGame)) {
            return false;
        }
        if (!checkPlayerMoveCount(pastGame, currentGame)) {
            return false;
        }
        return true;
    }

    private static GameStatus checkRows(Board board) {
        for (int i = 0; i < Board.row; i++) {
            int first = board.getCellState(i, 0);
            int second = board.getCellState(i, 1);
            int third = board.getCellState(i, 2);

            if (first != Board.empty && first == second && second == third) {
                if (first == Board.player) {
                    return GameStatus.PLAYER_WIN;
                } else if (first == Board.computer) {
                    return GameStatus.COMPUTER_WIN;
                }
            }

        }
        return null;
    }

    private static GameStatus checkColumns(Board board) {
        for (int i = 0; i < Board.col; i++) {
            int first = board.getCellState(0, i);
            int second = board.getCellState(1, i);
            int third = board.getCellState(2, i);
            if (first != Board.empty && first == second && second == third) {
                if (first == Board.player) {
                    return GameStatus.PLAYER_WIN;
                } else if (first == Board.computer) {
                    return GameStatus.COMPUTER_WIN;
                }
            }
        }
        return null;
    }

    private static GameStatus checkMainDiagonal(Board board) {
        int first, second, third;
        first = board.getCellState(0, 0);
        second = board.getCellState(1, 1);
        third = board.getCellState(2, 2);
        if (first != Board.empty && first == second && second == third) {
            if (first == Board.player) {
                return GameStatus.PLAYER_WIN;
            } else if (first == Board.computer) {
                return GameStatus.COMPUTER_WIN;
            }
        }
        return null;
    }

    private static GameStatus checkSideDiagonal(Board board) {
        int first, second, third;
        first = board.getCellState(2, 0);
        second = board.getCellState(1, 1);
        third = board.getCellState(0, 2);
        if (first != Board.empty && first == second && second == third) {
            if (first == Board.player) {
                return GameStatus.PLAYER_WIN;
            } else if (first == Board.computer) {
                return GameStatus.COMPUTER_WIN;
            }
        }
        return null;
    }

    private boolean checkEmptyCell(Board board) {
        for (int i = 0; i < Board.row; i++) {
            for (int j = 0; j < Board.col; j++) {
                if (board.isEmptyCell(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private GameStatus getBoardStatus(Board board) {
        if (board == null) {
            throw new IllegalArgumentException("Board cannot be null");
        }

        GameStatus status = checkRows(board);
        if (status != null) {
            return status;
        }

        status = checkColumns(board);
        if (status != null) {
            return status;
        }

        status = checkMainDiagonal(board);
        if (status != null) {
            return status;
        }

        status = checkSideDiagonal(board);
        if (status != null) {
            return status;
        }

        if (checkEmptyCell(board)) {
            return GameStatus.IN_PROGRESS;
        }

        return GameStatus.DRAW;
    }

    @Override
    public GameStatus getGameStatus(Game game) {
        if (game == null) {
            throw new IllegalArgumentException("Game cannot be null");
        }
        return getBoardStatus(game.getBoard());
    }

    private boolean isBoardEmpty(Board board) {
        if (board == null) {
            throw new IllegalArgumentException("Board cannot be null");
        }
        for (int i = 0; i < Board.row; i++) {
            for (int j = 0; j < Board.col; j++) {
                if (board.getCellState(i, j) != Board.empty) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Game processGame(UUID id, Game currentGame) {
        if (id == null || currentGame == null) {
            throw new IllegalArgumentException("Id and game must not be null");
        }
        Game pastGame = gameRepository.findById(id);
        if (pastGame == null) {
            throw new IllegalArgumentException("Game not found");
        }
        if (!id.equals(currentGame.getId())) {
            throw new IllegalArgumentException("Id does not match current game id");
        }
        if (isBoardEmpty(pastGame.getBoard()) && isBoardEmpty(currentGame.getBoard())) {
            Move move = findBestMove(currentGame);
            currentGame.getBoard().setCellState(move.getRow(), move.getCol(), Board.computer);
            return gameRepository.save(currentGame);
        }
        if (!validateGame(pastGame, currentGame)) {
            throw new IllegalArgumentException("Game is not valid");
        }
        if (getGameStatus(currentGame) != GameStatus.IN_PROGRESS) {
            return gameRepository.save(currentGame);
        }
        Move move = findBestMove(currentGame);
        currentGame.getBoard().setCellState(move.getRow(), move.getCol(), Board.computer);

        return gameRepository.save(currentGame);
    }

    public Game createGame(){
        Game game = new Game();
        return gameRepository.save(game);
    }
}
