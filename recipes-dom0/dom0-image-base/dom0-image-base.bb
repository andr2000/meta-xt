SUMMARY = "A console-only image that fully supports the target device \
hardware."

LICENSE = "GPLv2"

require ../dom0.inc

#select manifest source to build this domain: used by repo init to get layers
SRC_URI_salvator-x = "repo://renesas.com"
#IMAGE_INSTALL = "d"
