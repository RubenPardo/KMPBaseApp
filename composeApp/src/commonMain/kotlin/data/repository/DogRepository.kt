package data.repository

import base.domain.model.Failure
import base.domain.model.Result
import domain.model.DogModel
import domain.repository.DogRepository
import kotlinx.coroutines.delay

class DogRepositoryMock: DogRepository {
    override suspend fun getAll(): Result<Failure, List<DogModel>> {
        delay(1200)
        return Result.Success(
            listOf(
                DogModel(
                    "Shiba",
                    "https://images.dog.ceo/breeds/shiba/kurosuke01.jpg"
                ),
                DogModel(
                    "Affenpinscher",
                    "https://images.dog.ceo/breeds/affenpinscher/n02110627_1559.jpg"
                ),
                DogModel(
                    "Suvasz",
                    "https://images.dog.ceo/breeds/kuvasz/n02104029_1646.jpg"
                ),
                DogModel(
                    "African",
                    "https://images.dog.ceo/breeds/african/n02116738_10493.jpg"
                ),
                DogModel(
                    "Beagle",
                    "https://images.dog.ceo/breeds/beagle/n02088364_17473.jpg"
                ),
                DogModel(
                    "Newfoundland",
                    "https://images.dog.ceo/breeds/newfoundland/n02111277_3741.jpg"
                ),
                DogModel(
                    "Briard",
                    "https://images.dog.ceo/breeds/briard/n02105251_7058.jpg"
                ),
                DogModel(
                    "Borzoi",
                    "https://images.dog.ceo/breeds/borzoi/n02090622_8849.jpg"
                )
            )
        )
    }
}