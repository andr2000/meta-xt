def get_tarfname(location, d):
    import os

    return os.path.join(os.path.dirname(location),
        str(d.getVar('PN')) + "-" + str(d.getVar('PV')) + "-" +
        os.path.basename(location) + ".tar.gz")


def create_tarfile(location, d):
    import os, tarfile

    arcname = get_tarfname(location, d)
    tar = tarfile.open(arcname, "w:gz")
    tar.add(location, os.path.basename(location))
    return arcname

def check_url_or_pack(url, type, location, d):
    if type.lower() != 'file':
        return url
    if not os.path.isdir(location):
        return url
    new_location = create_tarfile(location, d)
    return url.replace(location, new_location)

def remove_tarfile(url, type, location, d):
    import os

    if type.lower() != 'file':
        return url
    if not os.path.isdir(location):
        return url
    arcname = get_tarfname(location, d)
    if os.path.exists(arcname):
        os.remove(arcname)

python do_unpack_prepend() {
    # now deliver all the extras into inner build system
    d.appendVar("SRC_URI", "\n")
    urls = (d.getVar("XT_QUIRCK_UNPACK_SRC_URI") or "").split()
    for url in urls:
        type, _, location, _, _, _ = bb.fetch.decodeurl(url)
        item = check_url_or_pack(url, type, location, d)
        d.appendVar("SRC_URI", item or "")
}

python do_unpack_append() {
    # remove the archives we created
    urls = (d.getVar("XT_QUIRCK_UNPACK_SRC_URI") or "").split()
    for url in urls:
        type, _, location, _, _, _ = bb.fetch.decodeurl(url)
        item = remove_tarfile(url, type, location, d)
}

python do_patch_prepend() {
    d.appendVar("SRC_URI", "\n")
    urls = (d.getVar("XT_QUIRCK_PATCH_SRC_URI") or "").split()
    d.appendVar("SRC_URI", d.getVar("XT_QUIRCK_PATCH_SRC_URI") or "")
}
