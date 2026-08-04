from pathlib import Path
from PIL import Image, ImageDraw, ImageFont


OUT = Path("Blockbench/palettes.png")

PALETTES = [
    ("ANDESITE ALLOY", [
        ("Highlight", "#C2CABB"),
        ("Light", "#AAB5A8"),
        ("Mid", "#89978D"),
        ("Shade", "#66746D"),
        ("Dark", "#46534F"),
        ("Deep detail", "#3A4646"),
        ("Outline", "#303B3B"),
    ]),
    ("IRON SHEETING", [
        ("Brightest", "#FCFDFE"),
        ("Highlight", "#F2F3F4"),
        ("Light 1", "#EBEEF0"),
        ("Light 2", "#E8EBED"),
        ("Mid-light", "#E5E9EB"),
        ("Mid", "#DEE3E6"),
        ("Shade 1", "#D7DDE1"),
        ("Shade 2", "#CFD6DA"),
        ("Edge light", "#979DA1"),
        ("Edge mid", "#878D91"),
        ("Edge dark", "#787E82"),
    ]),
    ("LEATHER", [
        ("Highlight", "#8B5A37"),
        ("Light", "#714529"),
        ("Mid", "#59331F"),
        ("Shade", "#432418"),
        ("Dark", "#2E190F"),
    ]),
    ("GOGGLE GOLD", [
        ("Highlight", "#F8DD72"),
        ("Mid", "#D6A23A"),
        ("Shade", "#9A651F"),
    ]),
    ("GOGGLE LENS", [
        ("Glint", "#B4D4E1"),
        ("Highlight", "#5BBCF4"),
        ("Mid", "#35C9D0"),
        ("Shade", "#168A9A"),
        ("Deep edge", "#536174"),
    ]),
    ("SPRUCE", [
        ("Highlight", "#906B3D"),
        ("Light 1", "#8A673E"),
        ("Light 2", "#816037"),
        ("Light 3", "#6D5B34"),
        ("Mid-light 1", "#675630"),
        ("Mid-light 2", "#6F512D"),
        ("Mid", "#644B2C"),
        ("Mid-dark", "#5D492A"),
        ("Shade 1", "#554727"),
        ("Shade 2", "#504224"),
        ("Dark", "#47341D"),
        ("Deep", "#382E17"),
    ]),
    ("DARK OAK", [
        ("Highlight", "#513719"),
        ("Light 1", "#4D3118"),
        ("Light 2", "#42321F"),
        ("Light 3", "#482E17"),
        ("Mid-light 1", "#3E301E"),
        ("Mid-light 2", "#3A2E1D"),
        ("Mid", "#372B1C"),
        ("Mid-dark", "#342A1B"),
        ("Shade 1", "#322819"),
        ("Shade 2", "#2F261A"),
        ("Dark", "#2C2316"),
        ("Deep", "#241D13"),
    ]),
    ("STURDY SHEET", [
        ("Highlight", "#8C8B9F"),
        ("Light 1", "#6F6D80"),
        ("Light 2", "#605F70"),
        ("Light 3", "#515060"),
        ("Mid-light", "#494858"),
        ("Mid", "#424150"),
        ("Mid-dark", "#373745"),
        ("Shade 1", "#292936"),
        ("Shade 2", "#21222D"),
        ("Dark", "#191B24"),
        ("Deep", "#0B0D15"),
        ("Outline", "#03050C"),
    ]),
    ("BRASS", [
        ("Brightest", "#FFFEF5"),
        ("Highlight 1", "#FFF9C7"),
        ("Highlight 2", "#FFF3AD"),
        ("Light", "#FFEC93"),
        ("Mid-light", "#FFCD67"),
        ("Gold", "#EDB75E"),
        ("Mid", "#DAA255"),
        ("Warm mid", "#BD7949"),
        ("Shade 1", "#AF6A43"),
        ("Shade 2", "#A05B3D"),
        ("Dark", "#7C462A"),
        ("Deep", "#652D1C"),
    ]),
]


def font(size: int, bold: bool = False):
    candidates = [
        Path("C:/Windows/Fonts/seguisb.ttf" if bold else "C:/Windows/Fonts/segoeui.ttf"),
        Path("C:/Windows/Fonts/arialbd.ttf" if bold else "C:/Windows/Fonts/arial.ttf"),
    ]
    for candidate in candidates:
        if candidate.exists():
            return ImageFont.truetype(str(candidate), size)
    return ImageFont.load_default()


W, H = 1600, 2060
BG = "#171A1D"
PANEL = "#22272B"
TEXT = "#F2F3F4"
MUTED = "#AEB8BE"
img = Image.new("RGB", (W, H), BG)
draw = ImageDraw.Draw(img)
title_font = font(46, True)
subtitle_font = font(22)
heading_font = font(25, True)
label_font = font(17)
hex_font = font(19, True)

draw.text((64, 48), "CREATE ARSENAL MATERIAL PALETTES", font=title_font, fill=TEXT)
draw.text((66, 108), "General material color reference for Blockbench models and textures", font=subtitle_font, fill=MUTED)

positions = [
    (64, 168, 1472, 210),
    (64, 400, 1472, 296),
    (64, 718, 600, 250),
    (680, 718, 352, 250),
    (1048, 718, 488, 250),
    (64, 990, 1472, 220),
    (64, 1232, 1472, 230),
    (64, 1484, 1472, 230),
    (64, 1736, 1472, 230),
]

for (name, colors), (x, y, width, height) in zip(PALETTES, positions):
    draw.rounded_rectangle((x, y, x + width, y + height), radius=18, fill=PANEL, outline="#343C42", width=2)
    draw.text((x + 22, y + 17), name, font=heading_font, fill=TEXT)
    gap = 12
    inner_x = x + 22
    inner_w = width - 44
    swatch_w = (inner_w - gap * (len(colors) - 1)) // len(colors)
    swatch_y = y + 60
    swatch_h = 78 if height < 270 else 102
    for index, (label, value) in enumerate(colors):
        sx = inner_x + index * (swatch_w + gap)
        draw.rounded_rectangle((sx, swatch_y, sx + swatch_w, swatch_y + swatch_h), radius=8, fill=value)
        draw.text((sx, swatch_y + swatch_h + 10), value, font=hex_font, fill=TEXT)
        draw.text((sx, swatch_y + swatch_h + 38), label, font=label_font, fill=MUTED)

draw.text((66, 2012), "Material reference only — excludes transparency, UV guides, mannequin colors, and placement markers.", font=subtitle_font, fill=MUTED)
OUT.parent.mkdir(parents=True, exist_ok=True)
img.save(OUT, optimize=True)
print(OUT.resolve())
