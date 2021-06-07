package com.nandaprasetio.gamecatalog.core.domain.entity.gamecreator

import com.nandaprasetio.gamecatalog.core.domain.entity.CreatorRole
import com.nandaprasetio.gamecatalog.core.domain.entity.game.GameShortInfo

class GameCreatorDetail(
    id: Int,
    name: String?,
    slug: String?,
    image: String?,
    imageBackground: String?,
    gamesCount: String?,
    creatorRole: List<CreatorRole>,
    games: List<GameShortInfo>
): GameCreator(id, name, slug, image, imageBackground, gamesCount, creatorRole, games)