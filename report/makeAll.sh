sh removeThings.sh
pdflatex W-BKSAMP.TEX
bibtex W-BKSAMP
pdflatex W-BKSAMP.TEX
makeindex W-BKSAMP.idx
pdflatex W-BKSAMP.TEX
open W-BKSAMP.pdf
