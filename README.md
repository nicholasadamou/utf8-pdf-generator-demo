# utf-8 PDF Generator Demo

This is a demo application that shows how to generate PDF files that contain international characters (e.g. Chinese, Japanese, Korean, etc.) using the [boxable](https://github.com/dhorions/boxable).

## Prerequisites

* Java 8
* Maven 3
* [boxable](https://github.com/dhorions/boxable)

## What is this?

[Boxable](https://github.com/dhorions/boxable) is a library that can be used to easily create tables in pdf documents. It uses the PDFBox PDF library under the hood. However, its documentation is non-existent which means its hard to determine how to support international characters. This demo application shows how to do that.

## How did this demo application come about?

In order to figure out how to actually solve this problem, I had to browse their issues and source code. I also had to do some trial and error. I figured that I would share my findings with the world so that others don't have to go through the same process.

A few issues that I found helpful:

* [Issue #230](https://github.com/dhorions/boxable/issues/230)
* [Issue #153](https://github.com/dhorions/boxable/issues/153)
* [Google Noto Fonts](https://www.google.com/get/noto/)
* [Arial Unicode MS](https://en.wikipedia.org/wiki/Arial_Unicode_MS)
* [Open Font License](https://scripts.sil.org/cms/scripts/page.php?site_id=nrsi&id=OFL)

## Install Dependencies

	mvn install

## Build

	mvn clean package

## Run

	mvn exec:java

## Output

The output is a PDF file called `example.pdf` that contains a table with international characters in each _default_ font.

## License

This project is licensed under the terms of the [MIT license](LICENSE).
