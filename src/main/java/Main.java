import be.quodlibet.boxable.BaseTable;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import be.quodlibet.boxable.utils.FontUtils;
// import untilities.Fonts;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		// Configure Log4j
		BasicConfigurator.configure();
		org.apache.log4j.Logger.getRootLogger().setLevel(Level.OFF);

		generatePDFDocument();
	}

	private static void generatePDFDocument() {
		final String FILE_NAME =  "example.pdf";

		String testString = GenerateTestString();

		System.out.printf("Generating file %s\n", FILE_NAME);

		try (PDDocument document = new PDDocument()) {
			PDPage page = new PDPage(PDRectangle.A4);

			// Fonts.loadFonts(document);
			FontUtils.setSansFontsAsDefault(document);

			document.addPage(page);

			// Start a new content stream which will "hold" the to be created content
			PDPageContentStream cos = new PDPageContentStream(document, page);

			java.util.Map<String, org.apache.pdfbox.pdmodel.font.PDFont> fonts = FontUtils.getDefaultfonts();

			// Table parameters
			float margin = 25;
			float yStartNewPage = page.getMediaBox().getHeight() - (margin);
			float tableWidth = page.getMediaBox().getWidth() - (14 * margin);
			float bottomMargin = 70;
			float yPosition = page.getMediaBox().getHeight() - (4 * margin);

			// Add title to the document
			cos.beginText();
			cos.setFont(fonts.get("fontBold"), 18);
			cos.newLineAtOffset(margin, page.getMediaBox().getHeight() - (2 * margin));
			cos.showText("Example PDF");
			cos.endText();

			// Add table with title
			cos.beginText();
			cos.setFont(fonts.get("fontItalic"), 14);
			cos.newLineAtOffset(margin, page.getMediaBox().getHeight() - (3 * margin));
			cos.showText("Font table");
			cos.endText();

			// Add table with content
			BaseTable table = new BaseTable(
					yPosition,
					yStartNewPage,
					bottomMargin,
					tableWidth,
					margin,
					document,
					page,
					true,
					true
			);

			be.quodlibet.boxable.Row<PDPage> row = table.createRow(15f);
			row.createCell(100, "Font kind");
			row.createCell(100, "Example text");

			row = table.createRow(15f);
			row.createCell(100, "Normal text");
			row.createCell(100, testString).setFont(fonts.get("font"));

			row = table.createRow(15f);
			row.createCell(100, "Bold text");
			row.createCell(100, testString).setFont(fonts.get("fontBold"));

			row = table.createRow(15f);
			row.createCell(100, "Italic text");
			row.createCell(100, testString).setFont(fonts.get("fontItalic"));

			row = table.createRow(15f);
			row.createCell(100, "Semi bold text");
			row.createCell(100, testString).setFont(fonts.get("fontBoldItalic"));

			table.draw();

			// close the content stream
			cos.close();

			// Save the results
			document.save(FILE_NAME);

			System.out.printf("\nSaved file %s\n", FILE_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String GenerateTestString() {
		// Generate a test string for each specific font
		String testString = """
		[ ] * ♥ & ¤ ¢ £ $ ¥ € § ¨ © ∞ ª ¬ ® ™ ¯ ° ± ´ µ ¶ … · ¸ º ÷ † ß ⁰¹²³⁴⁵⁶⁷⁸⁹ ₀₁₂₃₄₅₆₇₈₉ ½ ¼ ¾ ↑ ↗ ↓ → ← Ā ā Ā́ ā́ Ā̀ ā̀ Ā̂ ā̂ Ā͂ ā͂ Ǟ ǟ Ǡ ǡ Ǣ ǣ Ḇ
		ḇ C̄ c̄ Ḏ ḏ Ē ē Ḗ ḗ Ḕ ḕ Ē̂ ē̂ Ē͂ ē͂ Ḡ ḡ H̱ ẖ Ī ī Ī́ ī́ Ī̀ ī̀ Ī̂ ī̂ Ī͂ ī͂ Ḵ ḵ Ḻ ḻ Ḹ ḹ M̄ m̄ Ṉ ṉ N̄ n̄ Ō ō Ṓ ṓ Ṑ ṑ Ō̂ ō̂ Ō͂ ō͂ Ȫ ȫ Ǭ ǭ Ȭ ȭ Ȱ ȱ R̄ r̄ Ṟ ṟ Ṝ ṝ T̄ t̄ Ṯ ṯ Ū
		ū Ū́ ū́ Ū̀ ū̀ Ū̂ ū̂ Ū͂ ū͂ Ǖ ǖ Ṻ ṻ V̄ v̄ W̄ w̄ X̄ x̄ Ȳ ȳ Ȳ́ ȳ́ Ȳ̀ ȳ̀ Ȳ̂ ȳ̂ Ȳ͂ ȳ͂ Ẕ ẕ
		""";

		// Strip non-breaking spaces
		testString = testString.replaceAll("\u00A0", "");

		String[] lines = testString.split("\\n");
		ArrayList<String> list = new ArrayList<>();

		for (String line : lines) {
			if (!line.isBlank()) {
				list.add(line);
			}
		}

		testString = String.join(" ", list);

		// Strip test string of leading/trailing spaces and indentations
		testString = testString.stripLeading().stripTrailing().stripIndent();

		System.out.printf("Test string:\n%s\n\n", testString);

		return testString;
	}
}
