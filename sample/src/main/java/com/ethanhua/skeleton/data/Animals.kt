package com.ethanhua.skeleton.data

fun animalList(): List<Animal> =
    listOf(
        Animal(
            id = 1,
            name = "Horse",
            imageUrl = "https://www.vedovatipisos.com.br/wp-content/uploads/2016/11/Cavalo16-2.jpg",
            description = "The horse (Equus ferus caballus) is a domesticated one-toed hoofed mammal. It belongs to the taxonomic family Equidae and is one of two extant subspecies of Equus ferus. The horse has evolved over the past 45 to 55 million years from a small multi-toed creature, Eohippus, into the large, single-toed animal of today. Humans began domesticating horses around 4000 BC..."
        ),
        Animal(
            id = 2,
            name = "Cow",
            imageUrl = "https://super.abril.com.br/wp-content/uploads/2020/01/photograph-taken-by-alan-hopps_gettyimages_vacas_comunicacao.jpg",
            description = "Cattle, or cows (female) and bulls (male), are large domesticated cloven-hooved herbivores. They are a prominent modern member of the subfamily Bovinae, are the most widespread species of the genus Bos, and are most commonly classified collectively as Bos taurus..."
        ),
        Animal(
            id = 3,
            name = "Camel",
            imageUrl = "https://static.ricmais.com.br/uploads/2021/01/atacada-por-camelo.png",
            description = "A camel is an even-toed ungulate in the genus Camelus that bears distinctive fatty deposits known as \"humps\" on its back. Camels have long been domesticated and, as livestock, they provide food (milk and meat) and textiles (fiber and felt from hair)..."
        ),
        Animal(
            id = 4,
            name = "Sheep",
            imageUrl = "https://static.euronews.com/articles/stories/04/22/20/34/400x225_cmsv2_db4fff39-2646-55b7-9871-3c6361696187-4222034.jpg",
            description = "Sheep (Ovis aries) are quadrupedal, ruminant mammals typically kept as livestock. Like all ruminants, sheep are members of the order Artiodactyla, the even-toed ungulates. Although the name sheep applies to many species in the genus Ovis, in everyday usage it almost always refers to Ovis aries..."
        ),
        Animal(
            id = 5,
            name = "Goat",
            imageUrl = "https://cdn.akamai.steamstatic.com/steamcommunity/public/images/clans/5727978/79ff7821ae7b788a1918942cdb3621d78174dfe1_400x225.png",
            description = "The domestic goat or simply goat (Capra aegagrus hircus) is a subspecies of C. aegagrus domesticated from the wild goat of Southwest Asia and Eastern Europe. The goat is a member of the animal family Bovidae and the subfamily Caprinae, meaning it is closely related to the sheep. There are over 300 distinct breeds of goat..."
        )
    )