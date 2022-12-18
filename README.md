# SubOCR
SubOCR is a GUI tool that can create a timed subtitle (.srt) from cleared TXT images that are created by VideoSubFinder.
SubOCR utilizes the tesseract-ocr to recognize pictures and generate text from it. 

## Dependencies
 * [tesseract-ocr](https://github.com/tesseract-ocr/tesseract) 5.x
 * Minumum Java 1.8
 * [VideoSubFinder](https://sourceforge.net/projects/videosubfinder/) to create image files
 
## Setup
 * Download and install [tesseract-ocr](https://github.com/tesseract-ocr/tesseract) ver. 5.x
 * Download the right tessdata files according to your language (I recommend to use [tessdata-best](https://github.com/tesseract-ocr/tessdata_best) to the more accurate result)
 * Donwload and install SubOCR
 
## How to use
 * Create cleared text with VideoSubFinder (I recommend to use color filtering)
 * Run SubOCR
 * Locate Tesseract "tessdata" folder
 * Locate VideoSubFinder's TXTImage folder that contains subtitle images.
 * Select a folder where the .srt file will be created
 * Select your language. (eg. eng, rus, fr, etc.)
 * Click START OCR button
 * Wait for finish the process
 * Your converted.srt file will be created
 
## Build
Build with IntelliJ IDEA
- `./gradlew run` - run application
- `./gradlew package` - package native distribution into `build/compose/binaries`
