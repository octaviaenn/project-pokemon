import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class KarakterPokemon extends JPanel {
    private String selectedCharacter = null;
    private JFrame parentFrame;

    // Data Pokémon
    private static class PokemonData {
        String name;
        String type;
        int hp;
        int atk;
        int def;
        String basicMove;
        String specialMove;
        String elementalMove;

        PokemonData(String name, String type, int hp, int atk, int def, String basicMove, String specialMove,
                String elementalMove) {
            this.name = name;
            this.type = type;
            this.hp = hp;
            this.atk = atk;
            this.def = def;
            this.basicMove = basicMove;
            this.specialMove = specialMove;
            this.elementalMove = elementalMove;
        }
    }

    private final PokemonData[] pokemonDataList = {
            new PokemonData("Pikachu", "Electric", 35, 55, 40, "Quick Attack (Normal, 40)", "Flame Wheel (Fire, 60)",
                    "Thunderbolt (Electric, 90)"),
            new PokemonData("Bulbasaur", "Grass", 45, 49, 49, "Tackle (Normal, 40)", "Water Gun (Water, 40)",
                    "Seed Bomb (Grass, 80)"),
            new PokemonData("Squirtle", "Water", 44, 48, 65, "Tackle (Normal, 40)", "Thunder Shock (Electric, 40)",
                    "Bubble Beam (Water, 65)"),
            new PokemonData("Charmander", "Fire", 39, 52, 43, "Scratch(Normal, 40)", "Water Gun (Water, 40)",
                    "Flame Burst (Fire, 70)"),
            new PokemonData("Hoothoot", "Normal", 60, 30, 30, "Tackle (Normal, 40)", "Thunder Shock (Electric, 40)",
                    "Echoed Voice (Normal, 70)"),
            new PokemonData("Pidgey", "Normal", 40, 45, 40, "Tackle (Normal, 40)", "Flame Wheel (Fire, 60)",
                    "Quick Attack (Normal, 40)"),
            new PokemonData("Jigglypuff", "Normal", 115, 45, 20, "Pound (Normal, 40)", "Seed Bomb (Grass, 80)",
                    "Body Slam (Normal, 70)"),
            new PokemonData("Meowth", "Normal", 40, 45, 35, "Scratch (Normal, 40)", "Thunderbolt (Electric, 90)",
                    "Slash (Normal, 70)"),
            new PokemonData("Growlithe", "Fire", 55, 70, 45, "Bite (Normal, 60)", "Vine Whip (GrassALL, 45)",
                    "Flame Wheel (Fire, 60)"),
            new PokemonData("Eevee", "Normal", 55, 55, 50, "Quick Attack (Normal, 40)", "Water Gun (Water, 40)",
                    "Take Down (Normal, 90)"),
            new PokemonData("Flareon", "Fire", 65, 130, 60, "Tackle (Normal, 40)", "Thunderbolt (Electric, 90)",
                    "Flamethrower (Fire, 90)"),
            new PokemonData("Nidoran", "Normal", 55, 47, 52, "Scratch (Normal, 40)", "Vine Whip (Grass, 45)",
                    "Body Slam (Normal, 70)"),
            new PokemonData("Clefairy", "Normal", 70, 45, 48, "Pound (Normal, 40)", "Water Gun (Water, 40)",
                    "Headbutt (Normal, 70)"),
            new PokemonData("Abra", "Normal", 25, 20, 15, "Tackle (Normal, 40)", "Thunder Shock (Electric, 40)",
                    "Headbutt (Normal, 70)"),
            new PokemonData("Sandshrew", "Normal", 250, 5, 5, "Pound (Normal, 40)", "Thunder Shock (Electric, 40)",
                    "Double-Edge (Normal, 120)")
    };

    public KarakterPokemon() {
        // setTitle("Pemilihan Karakter Pokemon");
        setSize(1400, 750);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel utama dengan background gambar
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image backgroundImage = new ImageIcon("assets/bg-character.png")
                            .getImage();
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    System.err.println("Kesalahan memuat gambar background: " + e.getMessage());
                }
            }
        };
        mainPanel.setLayout(new BorderLayout(10, 10));

        JPanel characterPanel = new JPanel(new GridLayout(3, 5, 10, 15));
        characterPanel.setBorder(BorderFactory.createEmptyBorder(40, 15, 0, 15));
        characterPanel.setOpaque(false);
        // Daftar nama Pokemon
        String[] pokemonNames = {
                "Pikachu", "Bulbasaur", "Squirtle", "Charmander", "Hoothoot",
                "Pidgey", "Jigglypuff", "Meowth", "Growlithe", "Eevee",
                "Flareon", "Nidoran", "Clefairy", "Abra", "Sandshrew"
        };

        // Kartu untuk setiap Pokémon
        for (String name : pokemonNames) {
            JPanel card = createCharacterCard(name);
            characterPanel.add(card);
        }

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));

        BufferedImage backImage = null;
        try {
            backImage = ImageIO.read(new File("assets//home.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image back = backImage.getScaledInstance(120, 60, Image.SCALE_SMOOTH);
        JButton backButton = new JButton(new ImageIcon(back));
        // JButton backButton = new JButton();
        // try {
        // ImageIcon backIcon = new ImageIcon("assets/home.png");
        // if (backIcon.getImage() != null) {
        // Image scaledBackImage = backIcon.getImage().getScaledInstance(140, 50,
        // Image.SCALE_SMOOTH);
        // backButton.setIcon(new ImageIcon(scaledBackImage));
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        // } else {
        // backButton.setText("Back");
        // }
        // } catch (Exception e) {
        // backButton.setText("Back");
        // System.err.println("Kesalahan memuat gambar back_icon: " + e.getMessage());
        // }
        // backButton.setPreferredSize(new Dimension(140, 50));
        backButton.addActionListener(e -> {
            if (parentFrame != null) {
                Homepage hp = new Homepage(parentFrame);
                parentFrame.setContentPane(hp.getMainPanel());
                parentFrame.revalidate();
                parentFrame.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Kembali ke halaman Home!");
            }
        });
        // Tombol Start
        BufferedImage startImage = null;
        try {
            startImage = ImageIO.read(new File("assets//start.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image start = startImage.getScaledInstance(120, 60, Image.SCALE_SMOOTH);
        JButton startButton = new JButton(new ImageIcon(start));

        // JButton startButton = new JButton();
        // try {
        // ImageIcon startIcon = new ImageIcon("assets/start.png");
        // if (startIcon.getImage() != null) {
        // Image scaledStartImage = startIcon.getImage().getScaledInstance(200, 180,
        // Image.SCALE_SMOOTH);
        // startButton.setIcon(new ImageIcon(scaledStartImage));
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        // } else {
        // startButton.setText("Start");
        // }
        // } catch (Exception e) {
        // startButton.setText("Start");
        // System.err.println("Kesalahan memuat gambar start : " + e.getMessage());
        // }
        // startButton.setPreferredSize(new Dimension(200, 180));
        startButton.addActionListener(e -> {
            if (selectedCharacter != null) {
                JOptionPane.showMessageDialog(this, "Memulai permainan dengan " + selectedCharacter + "!");
                // ambil characternya
            } else {
                JOptionPane.showMessageDialog(this, "Silakan pilih karakter terlebih dahulu!");
            }
        });

        // Place buttons in BorderLayout
        buttonPanel.add(backButton, BorderLayout.WEST); // Tombol Home di kiri bawah
        buttonPanel.add(startButton, BorderLayout.EAST); // Tombol Start di kanan bawah

        mainPanel.add(characterPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    // Kartu karakter
    private JPanel createCharacterCard(String pokemonName) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        card.setBackground(new Color(0xDCA0A6));

        // GIF
        String gifPath = switch (pokemonName) {
            case "Pikachu" -> "assets\\charmon\\pikachu-f.gif";
            case "Bulbasaur" -> "assets\\charmon\\bulbasaur-f.gif";
            case "Squirtle" -> "assets\\charmon\\squirtle-f.gif";
            case "Charmander" -> "assets\\charmon\\charmander-f.gif";
            case "Hoothoot" -> "assets\\charmon\\hoothoot-f.gif";
            case "Pidgey" -> "assets\\charmon\\pidgey-f.gif";
            case "Jigglypuff" -> "assets\\charmon\\jigglypuff-f.gif";
            case "Meowth" -> "assets\\charmon\\meowth-f.gif";
            case "Growlithe" -> "assets\\charmon\\growlithe-f.gif";
            case "Eevee" -> "assets\\charmon\\eevee-f.gif";
            case "Flareon" -> "assets\\charmon\\flareon-f.gif";
            case "Nidoran" -> "assets\\charmon\\nidoran-f.gif";
            case "Clefairy" -> "assets\\charmon\\clefairy-f.gif";
            case "Abra" -> "assets\\charmon\\abra-f.gif";
            case "Sandshrew" -> "assets\\charmon\\Sandshrew-f.gif";
            default -> "assets\\charmon\\default.gif";
        };

        JLabel imageLabel;
        try {
            // java.net.URL imgURL = getClass().getResource(gifPath);
            imageLabel = new JLabel(new ImageIcon(gifPath));
        } catch (Exception e) {
            imageLabel = new JLabel(pokemonName + " Image", SwingConstants.CENTER);
            imageLabel.setBackground(new Color(0xDCA0A6));
            imageLabel.setOpaque(true);
            System.err.println("Kesalahan memuat gambar " + gifPath + ": " + e.getMessage());
        }

        imageLabel.setPreferredSize(new Dimension(80, 80));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Label utk nama Pokemon
        JLabel nameLabel = new JLabel(pokemonName, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        card.add(imageLabel, BorderLayout.CENTER);
        card.add(nameLabel, BorderLayout.SOUTH);

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showCharacterPopup(pokemonName);
            }
        });
        return card;
    }
    // Pop-up detail karakter
    private void showCharacterPopup(String pokemonName) {
        Window parentWindow=parentFrame != null ? parentFrame: SwingUtilities.getWindowAncestor(this);
        JDialog popup = new JDialog(parentWindow, pokemonName + " Details", Dialog.ModalityType.APPLICATION_MODAL);
        popup.setSize(600, 400); // Ukuran tetap 600x400
        popup.setLocationRelativeTo(this);
        popup.setLayout(new BorderLayout(5, 5));

        JPanel backgroundPanel = new JPanel(new BorderLayout());
        backgroundPanel.setBackground(new Color(0xDCA0A6));
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Content Panel to hold image and info
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);

        // Image Panel with left padding
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(new Color(0xDCA0A6));
        imagePanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0)); // Added 20px left padding
        String gifPath = switch (pokemonName) {
            case "Pikachu" -> "assets\\charmon\\pikachu-f.gif";
            case "Bulbasaur" -> "assets\\charmon\\bulbasaur-f.gif";
            case "Squirtle" -> "assets\\charmon\\squirtle-f.gif";
            case "Charmander" -> "assets\\charmon\\charmander-f.gif";
            case "Hoothoot" -> "assets\\charmon\\hoothoot-f.gif";
            case "Pidgey" -> "assets\\charmon\\pidgey-f.gif";
            case "Jigglypuff" -> "assets\\charmon\\jigglypuff-f.gif";
            case "Meowth" -> "assets\\charmon\\meowth-f.gif";
            case "Growlithe" -> "assets\\charmon\\growlithe-f.gif";
            case "Eevee" -> "assets\\charmon\\eevee-f.gif";
            case "Flareon" -> "assets\\charmon\\flareon-f.gif";
            case "Nidoran" -> "assets\\charmon\\nidoran-f.gif";
            case "Clefairy" -> "assets\\charmon\\clefairy-f.gif";
            case "Abra" -> "assets\\charmon\\abra-f.gif";
            case "Sandshrew" -> "assets\\charmon\\Sandshrew-f.gif";
            default -> "assets\\charmon\\default.gif";
        };

        JLabel imageLabel;
        try {
            // java.net.URL imgURL = gifPath;
            imageLabel = new JLabel(new ImageIcon(gifPath));
        } catch (Exception e) {
            imageLabel = new JLabel(pokemonName + " Image", SwingConstants.CENTER);
            imageLabel.setBackground(Color.LIGHT_GRAY);
            imageLabel.setOpaque(true);
            System.err.println("Kesalahan memuat gambar " + gifPath + ": " + e.getMessage());
        }

        imageLabel.setPreferredSize(new Dimension(200, 150));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        contentPanel.add(imagePanel, BorderLayout.WEST);

        // Info Panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));
        infoPanel.setOpaque(false);

        infoPanel.add(Box.createVerticalStrut(20)); // Increased space at top

        PokemonData pokemon = null;
        for (PokemonData data : pokemonDataList) {
            if (data.name.equalsIgnoreCase(pokemonName)) {
                pokemon = data;
                break;
            }
        }

        if (pokemon != null) {
            JLabel nameLabel = new JLabel("Name : " + pokemon.name);
            nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            infoPanel.add(nameLabel);
            infoPanel.add(Box.createVerticalStrut(10)); // Increased spacing

            JLabel typeLabel = new JLabel("Type : " + pokemon.type);
            typeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            infoPanel.add(typeLabel);
            infoPanel.add(Box.createVerticalStrut(10));

            JLabel hpLabel = new JLabel("HP : " + pokemon.hp);
            hpLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            infoPanel.add(hpLabel);
            infoPanel.add(Box.createVerticalStrut(10));

            JLabel atkLabel = new JLabel("Attack : " + pokemon.atk);
            atkLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            infoPanel.add(atkLabel);
            infoPanel.add(Box.createVerticalStrut(10));

            JLabel defLabel = new JLabel("Defense : " + pokemon.def);
            defLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            infoPanel.add(defLabel);
            infoPanel.add(Box.createVerticalStrut(10));

            JLabel basicMoveLabel = new JLabel("Basic Move : " + pokemon.basicMove);
            basicMoveLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            infoPanel.add(basicMoveLabel);
            infoPanel.add(Box.createVerticalStrut(10));

            JLabel specialMoveLabel = new JLabel("Special Move : " + pokemon.specialMove);
            specialMoveLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            infoPanel.add(specialMoveLabel);
            infoPanel.add(Box.createVerticalStrut(10));

            JLabel elementalMoveLabel = new JLabel("Elemental Move : " + pokemon.elementalMove);
            elementalMoveLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            infoPanel.add(elementalMoveLabel);
        } else {
            JLabel nameLabel = new JLabel("Name : " + pokemonName);
            nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            infoPanel.add(nameLabel);
            infoPanel.add(Box.createVerticalStrut(10));

            JLabel typeLabel = new JLabel("Type : - ");
            typeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            infoPanel.add(typeLabel);
            infoPanel.add(Box.createVerticalStrut(10));

            JLabel hpLabel = new JLabel("HP : - ");
            hpLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            infoPanel.add(hpLabel);
            infoPanel.add(Box.createVerticalStrut(10));

            JLabel atkLabel = new JLabel("Attack : - ");
            atkLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            infoPanel.add(atkLabel);
            infoPanel.add(Box.createVerticalStrut(10));

            JLabel defLabel = new JLabel("Defense : - ");
            defLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            infoPanel.add(defLabel);
            infoPanel.add(Box.createVerticalStrut(10));

            JLabel basicMoveLabel = new JLabel("Basic Move : - ");
            basicMoveLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            infoPanel.add(basicMoveLabel);
            infoPanel.add(Box.createVerticalStrut(10));

            JLabel specialMoveLabel = new JLabel("Special Move : - ");
            specialMoveLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            infoPanel.add(specialMoveLabel);
            infoPanel.add(Box.createVerticalStrut(10));

            JLabel elementalMoveLabel = new JLabel("Elemental Move : - ");
            elementalMoveLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            infoPanel.add(elementalMoveLabel);
        }

        contentPanel.add(infoPanel, BorderLayout.CENTER);
        backgroundPanel.add(contentPanel, BorderLayout.CENTER);

        // Select Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(2, 0, 3, 0));
        BufferedImage selectImage = null;
        try {
            selectImage = ImageIO.read(new File("assets//select.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image select = selectImage.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
        JButton selectButton = new JButton(new ImageIcon(select));
        // selectButton.setBackground(new Color(0xAE757F));
        // selectButton.setForeground(Color.BLACK);
        // selectButton.setFont(new Font("Arial", Font.BOLD, 14));
        selectButton.setFocusPainted(false);
        // selectButton.setPreferredSize(new Dimension(120, 50));
        selectButton.setContentAreaFilled(false);
        selectButton.addActionListener(e -> {
            selectedCharacter = pokemonName;
            JOptionPane.showMessageDialog(popup, pokemonName + " dipilih!");
            popup.dispose();
        });

        buttonPanel.add(selectButton);
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        popup.add(backgroundPanel, BorderLayout.CENTER);
        popup.setVisible(true);
    }

    // public static void main(String[] args) {
    // SwingUtilities.invokeLater(() -> {
    // new KarakterPokemon().setVisible(true);
    // });
    // }
}