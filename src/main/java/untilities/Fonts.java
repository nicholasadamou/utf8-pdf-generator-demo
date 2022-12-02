package untilities;

import be.quodlibet.boxable.utils.FontUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Fonts {
	private static final ClassLoader classLoader = Fonts.class.getClassLoader();

//	private static final File ARIAL_UNICODE_MS = new File(Objects.requireNonNull(classLoader.getResource("fonts/arialuni.ttf")).getFile());
	private static final File NOTO_SANS_MONO_REGULAR = new File(Objects.requireNonNull(classLoader.getResource("fonts/NotoSansMono/NotoSansMono-Regular.ttf")).getFile());
	private static final File NOTO_SANS_MONO_BOLD = new File(Objects.requireNonNull(classLoader.getResource("fonts/NotoSansMono/NotoSansMono-Bold.ttf")).getFile());
	private static final File NOTO_SANS_MONO_LIGHT = new File(Objects.requireNonNull(classLoader.getResource("fonts/NotoSansMono/NotoSansMono-Light.ttf")).getFile());
	private static final File NOTO_SANS_MONO_SEMI_BOLD = new File(Objects.requireNonNull(classLoader.getResource("fonts/NotoSansMono/NotoSansMono-SemiBold.ttf")).getFile());

	public static void loadFonts(PDDocument document) throws IOException {
		System.out.println();

//		PDFont font = PDType0Font.load(document, ARIAL_UNICODE_MS);

      	PDFont font = PDType0Font.load(document, NOTO_SANS_MONO_REGULAR);
		PDFont boldFont = PDType0Font.load(document, NOTO_SANS_MONO_BOLD);
		PDFont italicFont = PDType0Font.load(document, NOTO_SANS_MONO_LIGHT);
		PDFont semiBoldFont = PDType0Font.load(document, NOTO_SANS_MONO_SEMI_BOLD);

		System.out.printf("Loaded font: %s\n", font);
		System.out.printf("Loaded font: %s\n", boldFont);
		System.out.printf("Loaded font: %s\n", italicFont);
		System.out.printf("Loaded font: %s\n", semiBoldFont);

		System.out.println();

		FontUtils.addDefaultFonts(font, boldFont, italicFont, semiBoldFont);
//		FontUtils.addDefaultFonts(font, font, font, font);

		System.out.printf("Set font(s) as default: %s\n", FontUtils.getDefaultfonts());
	}
}
