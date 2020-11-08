package com.gabriel.lunala.project.util

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = PremiumSerializer::class)
enum class PremiumType(val id: Int) {

    NONE(0),
    STAR(1),
    SUPERNOVA(2),
    BLACK_HOLE(3);

    companion object {
        fun findById(id: Int): PremiumType? = values().firstOrNull {
            it.id == id
        }
    }

}

class PremiumSerializer: KSerializer<PremiumType> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("premium_type", PrimitiveKind.INT)

    override fun deserialize(decoder: Decoder): PremiumType =
        PremiumType.findById(decoder.decodeInt())!!

    override fun serialize(encoder: Encoder, value: PremiumType) =
        encoder.encodeInt(value.ordinal)
}